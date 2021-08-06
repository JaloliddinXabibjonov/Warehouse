package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.*;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    //CREATE
    public Result add(OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday mahsulot topilmadi", false);
        if (!optionalProduct.get().isActive())
            return new Result("Ushbu mahsulot aktiv emas", false);
        OutputProduct outputProduct=new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());

        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Bunday chiqim topilmadi", false);
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Chiqim mahsuloti saqlandi", true);
    }

    //GET ALL
    public List<OutputProduct> getAll(){
        return outputProductRepository.findAll();
    }

    //GET ONE BY ID
    public Result getById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("Bunday chiqim mahsuloti topilmadi", false);
        return new Result("Muvaffaqiyatli bajarildi", true, optionalOutputProduct.get());
    }

    //UPDATE
    public Result edit(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> optional = outputProductRepository.findById(id);
        if (!optional.isPresent())
            return new Result("Bunday chiqim mahsuloti topilmadi", false);
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday mahsulot topilmadi", false);
        if (!optionalProduct.get().isActive())
            return new Result("Ushbu mahsulot aktiv emas", false);
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Bunday chiqim topilmadi", false);
        OutputProduct outputProduct = optional.get();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());

        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Chiqim mahsuloti tahrirlandi", true);
    }

    //DELETE
    public Result delete(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(!optionalOutputProduct.isPresent())
            return new Result("Bunday chiqim mahsuloti topilmadi", false);
        outputProductRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }
}
