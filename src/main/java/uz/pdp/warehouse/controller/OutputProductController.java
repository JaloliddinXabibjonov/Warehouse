package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.OutputProduct;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.add(outputProductDto);
    }

    @GetMapping
    public List<OutputProduct> getAll(){
        return outputProductService.getAll();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        return outputProductService.getById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.edit(id, outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return outputProductService.delete(id);
    }


}
