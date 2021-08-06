package uz.pdp.warehouse.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductDto {
    private Integer productId;
    private Double amount;
    private Double price;
    private Date expireDate;
    private Integer inputId;
}
