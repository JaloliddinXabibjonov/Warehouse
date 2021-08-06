package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.*;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;

    //CREATE
    public Result add(OutputDto outputDto){
        Output output=new Output();
        //SET DATE
        Timestamp timestamp=new Timestamp((new Date()).getTime());
        output.setDate(timestamp);

        //CHECK WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor topilmadi", false);

        //CHECK SUPPLIER
        Optional<Client> optionalSupplier = clientRepository.findById(outputDto.getClientId());
        if (!optionalSupplier.isPresent())
            return new Result("Bunday xaridor topilamdi", false);

        //CHECK CURRENCY
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday valyuta topilmadi", false);

        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalSupplier.get());
        output.setCurrency(optionalCurrency.get());
        Integer maxId = outputRepository.maxId();
        if (maxId==null){
            maxId=1;
        }
        else{
            maxId+=1;
        }
        output.setCode(maxId.toString());
        return new Result("Chiqim muvaffaqiyatli saqlandi", true);

    }

    //READ ALL
    public List<Output> getAll(){
        return outputRepository.findAll();
    }

    //GET BY ID
    public Result getById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent())
            return new Result("Muvaffaqiyatli bajarildi", true, optionalOutput.get());
        return new Result("Bunday chiqim topilmadi", false);
    }

    //UPDATE
    public Result edit(Integer id, OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("Bunday chiqim topilmadi", false);
        Output output = optionalOutput.get();
        //SET DATE
        Timestamp timestamp=new Timestamp((new Date()).getTime());
        output.setDate(timestamp);

        //CHECK WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor topilmadi", false);

        //CHECK SUPPLIER
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Bunday xaridor topilamdi", false);

        //CHECK CURRENCY
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday valyuta topilmadi", false);

        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        Integer maxId = outputRepository.maxId();
        if (maxId==null){
            maxId=1;
        }
        else{
            maxId+=1;
        }
        output.setCode(maxId.toString());
        return new Result("Chiqim muvaffaqiyatli tahrirlandi", true);

    }

    //DELETE
    public Result delete(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("Bunday chiqim mavjud emas", false);
        outputRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }


}
