package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result add(@RequestBody Warehouse warehouse){
        return warehouseService.add(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAll(){
        return warehouseService.getAll();
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable Integer id){
        return warehouseService.getOne(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.edit(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return warehouseService.delete(id);
    }
}
