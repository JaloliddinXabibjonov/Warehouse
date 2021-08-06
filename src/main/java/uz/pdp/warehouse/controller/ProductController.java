package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody ProductDto productDto){
        return productService.add(productDto);
    }

    //READ
    @GetMapping
    public List<Product> get(){
        return productService.getAll();
    }

    //UPDATE
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.edit(id, productDto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return productService.delete(id);
    }
}
