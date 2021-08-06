package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CurrenyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrenyService currenyService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody Currency currency){
        return currenyService.addCurrency(currency);
    }
    //GET ALL
    @GetMapping
    public List<Currency> getAll(){
        return currenyService.getAll();
    }

    //GET ONE
    @GetMapping("/{id}")
    public Result getOne(@PathVariable Integer id){
        return currenyService.getOne(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Currency currency){
        return currenyService.edit(id, currency);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return currenyService.delete(id);
    }
}
