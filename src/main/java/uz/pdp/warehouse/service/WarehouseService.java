package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    //CREATE
    public Result add(Warehouse warehouse){
        boolean warehouseByName = warehouseRepository.existsWarehouseByName(warehouse.getName());
        if (warehouseByName)
            return new Result("Bunday ombor avval qo`shilgan", false);
        warehouseRepository.save(warehouse);
        return new Result("Ombor muvaffaqiyatli qo`shildi", true);
    }

    //READ ALL
    public List<Warehouse> getAll(){
        return warehouseRepository.findAll();
    }

    //GET ONE BY ID
    public Result getOne(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        return new Result("Muvaffaqiyatli bajarildi" ,true, optionalWarehouse.get());
    }

    //UPDATE
    public Result edit(Integer id, Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor topilmadi", false);

        //CHECK UNIQUE NAME
        Warehouse editingWarehouse = optionalWarehouse.get();
        if (!editingWarehouse.getName().equals(warehouse.getName())){
            boolean byName = warehouseRepository.existsWarehouseByName(warehouse.getName());
            if (byName)
                return new Result("Bunday ombor avval kiritilgan", false);
        }
        warehouseRepository.save(editingWarehouse);
        return new Result("Ombor muvaffaqiyatli tahrirlandi", true);
    }

    //DELETE
    public Result delete(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()){
            warehouseRepository.deleteById(id);
            return new Result("Ombor muvaffaqiyatli o`chirildi", true);
        }
        return new Result("Bunday ombor topilmadi", false);
    }
}
