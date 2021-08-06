package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody Supplier supplier){
        return supplierService.add(supplier);
    }

    //READ
    @GetMapping
    public List<Supplier > getAll(){
        return supplierService.getAll();
    }

    //GET ONE
    @GetMapping("/{id}")
    public Result getOne(@PathVariable Integer id){
        return supplierService.getOne(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Supplier supplier){
        return supplierService.edit(id, supplier);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return supplierService.delete(id);
    }

}
