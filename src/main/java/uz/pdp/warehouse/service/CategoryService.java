package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

                        //CREATE

    public Result addCategoryService(@RequestBody CategoryDto categoryDto){
        Category category=new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("Bunday ota kategoriya mavjud emas", false);
            category.setCategoryParent(optionalCategory.get());

        }
        categoryRepository.save(category);
        return new Result("Kategoriya saqlandi", true);
    }

                    //READ

    public List<Category> getCategoryService(){
        return categoryRepository.findAll();
    }

                    //UPDATE

    public Result editCategoryService(Integer id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas", false);
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> optionalCategori = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategori.isPresent())
                return new Result("Bunday ota kategoriya mavjud emas", false);
            category.setCategoryParent(optionalCategori.get());

        }
        categoryRepository.save(category);
        return new Result("Kategoriya tahrirlandi", true);

    }

                    //DELETE
    public Result deleteCategoryService(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas", false);
        categoryRepository.deleteById(id);
        return new Result("Kategoriya muvaffaqiyatli o`chirildi", true);
    }
}
