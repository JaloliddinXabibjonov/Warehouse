package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductDto {
    private String name;
    private  Integer categoryId;
    private  Integer photoId;
    private  Integer measurementId;


}
