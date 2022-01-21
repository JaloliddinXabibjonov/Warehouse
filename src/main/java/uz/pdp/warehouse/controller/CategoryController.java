package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    //CREATE
    @PostMapping("/add")
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategoryService(categoryDto);
    }

    //READ
    @GetMapping("/getAll")
    public List<Category>  getCategory(){
        return categoryService.getCategoryService();
    }

    //UPDATE
    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.editCategoryService(id, categoryDto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategoryService(id);
    }
}
