package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    //CREATE
    public Result add(Supplier supplier) {
        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Bunday telefon raqami avval kiritilgan", false);
        supplierRepository.save(supplier);
        return new Result("Yetkazib beruvchi muvaffaqiyatli qo`shildi", true);
    }

    //READ ALL
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    //GET ONE
    public Result getOne(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            return new Result("Muvaffaqiyatli bajarildi", true, optionalSupplier.get());
        }
        return new Result("Bunday yetkazib beruvchi topilmadi", false);
    }

    //UPDATE
    public Result edit(Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier editingSupplier = optionalSupplier.get();
            editingSupplier.setName(supplier.getName());
            if (!editingSupplier.getPhoneNumber().equals(supplier.getPhoneNumber())) {
                boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
                if (existsByPhoneNumber)
                    return new Result("Bunday telefon raqami avval kiritilgan", false);
                editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
            }
            supplierRepository.save(supplier);
        }
        return new Result("Yetkazib beruvchi muvaffaqiyatli qo`zgartirildi", true);

    }

    //DELETE
    public Result delete(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Bunday yetkazib beruvchi mavjud emas", false);
        supplierRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }


}
