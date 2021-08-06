package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentRepository;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.repository.MeasurementRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;
                                                            //ADD PRODUCT
    public Result add( ProductDto productDto){

        //CHECK PRODUCT NAME AND CATEGORY ID
        boolean existsProductByNameAndCategory_id = productRepository.existsProductByNameAndCategory_Id(productDto.getName(), productDto.getCategoryId());
        if (existsProductByNameAndCategory_id)
            return new Result("Bunday mahsulot ushbu kategoriyada mavjud", false);

        //CHECK CATEGORY
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya topilmadi", false);
        if (!optionalCategory.get().isActive())
            return new Result("Ushbu kategoriya faol emas", false);

        //CHECK PHOTO
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday rasm mavjud emas", false);

        //CHECK MEASUREMENT
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o`lchov birligi topilmadi",false);
        if (!optionalMeasurement.get().isActive())
            return new Result("Ushbu o`lchov birligi faol emas", false);

        //CREATE CODE
        Integer maxId=productRepository.maxId();
        if (maxId==null)
            maxId=1;
        else
            maxId+=1;
        Product product=new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCode(String.valueOf(maxId));
        productRepository.save(product);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

                                                            //GET ALL PRODUCT
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    //GET PRODUCT BY ID
    public Result getById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Bunday mahsulot topilmadi", false);
        return new Result("Muvaffaqiyatli bajarildi", true,optionalProduct.get());
    }

    //UPDATE PRODUCT
    public Result edit(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent())
            return new Result("Mahsulot topilmadi", false);

        //CHECK CATEGORY
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya topilmadi", false);
        if (!optionalCategory.get().isActive())
            return new Result("Ushbu kategoriya faol emas", false);

        //CHECK PHOTO
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday rasm mavjud emas", false);

        //CHECK MEASUREMENT
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o`lchov birligi topilmadi",false);
        if (!optionalMeasurement.get().isActive())
            return new Result("Ushbu o`lchov birligi faol emas", false);

        //CREATE CODE
        Integer maxId=productRepository.maxId();
        if (maxId==null)
            maxId=1;
        else
            maxId+=1;
        Product product=optionalProduct.get();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCode(String.valueOf(maxId));
        productRepository.save(product);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    //DELETE
    public Result delete(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Bunday mahsulot mavjud emas", false);
        productRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }

}

