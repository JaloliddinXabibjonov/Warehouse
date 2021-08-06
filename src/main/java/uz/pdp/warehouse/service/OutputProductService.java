package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.InputProductRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.OutputProductRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    //CREATE
    public Result add(InputProductDto inputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday mahsulot topilmadi", false);
        if (!optionalProduct.get().isActive())
            return new Result("Ushbu mahsulot aktiv emas", false);
        InputProduct inputProduct=new InputProduct();
        inputProduct.setProduct(optionalProduct.get());

        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Bunday kirim topilmadi", false);
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Kirim mahsuloti saqlandi", true);
    }

    //GET ALL
    public List<InputProduct> getAll(){
        return inputProductRepository.findAll();
    }

    //GET ONE BY ID
    public Result getById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("Bunday kirim mahsuloti topilmadi", false);
        return new Result("Muvaffaqiyatli bajarildi", true, optionalInputProduct.get());
    }

    //UPDATE
    public Result edit(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optional = inputProductRepository.findById(id);
        if (!optional.isPresent())
            return new Result("Bunday kirim mahsuloti topilmadi", false);
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Bunday mahsulot topilmadi", false);
        if (!optionalProduct.get().isActive())
            return new Result("Ushbu mahsulot aktiv emas", false);
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Bunday kirim topilmadi", false);
        InputProduct inputProduct = optional.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());

        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Kirim mahsuloti tahrirlandi", true);
    }

    //DELETE
    public Result delete(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(!optionalInputProduct.isPresent())
            return new Result("Bunday kirim mahsuloti topilmadi", false);
        inputProductRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }
}
