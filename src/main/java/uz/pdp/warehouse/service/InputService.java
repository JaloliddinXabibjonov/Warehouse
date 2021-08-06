package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.SupplierRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;

    //CREATE
    public Result add(InputDto inputDto){
        Input input=new Input();
        //SET DATE
        Timestamp timestamp=new Timestamp((new Date()).getTime());
        input.setDate(timestamp);

        //CHECK WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor topilmadi", false);

        //CHECK SUPPLIER
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Bunday yetkazib beruvchi topilamdi", false);

        //CHECK CURRENCY
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday valyuta topilmadi", false);

        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        Integer maxId = inputRepository.maxId();
        if (maxId==null){
            maxId=1;
        }
        else{
            maxId+=1;
        }
        input.setCode(maxId.toString());
        return new Result("Kirim muvaffaqiyatli saqlandi", true);

    }

    //READ ALL
    public List<Input> getAll(){
        return inputRepository.findAll();
    }

    //GET BY ID
    public Result getById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent())
            return new Result("Muvaffaqiyatli bajarildi", true, optionalInput.get());
        return new Result("Bunday kirim topilmadi", false);
    }

    //UPDATE
    public Result edit(Integer id, InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Bunday kirim topilmadi", false);
        Input input = optionalInput.get();
        //SET DATE
        Timestamp timestamp=new Timestamp((new Date()).getTime());
        input.setDate(timestamp);

        //CHECK WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor topilmadi", false);

        //CHECK SUPPLIER
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Bunday yetkazib beruvchi topilamdi", false);

        //CHECK CURRENCY
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday valyuta topilmadi", false);

        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        Integer maxId = inputRepository.maxId();
        if (maxId==null){
            maxId=1;
        }
        else{
            maxId+=1;
        }
        input.setCode(maxId.toString());
        return new Result("Kirim muvaffaqiyatli tahrirlandi", true);

    }

    //DELETE
    public Result delete(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Bunday kirim mavjud emas", false);
        inputRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }


}
