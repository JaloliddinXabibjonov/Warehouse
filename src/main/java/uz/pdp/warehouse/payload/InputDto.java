package uz.pdp.warehouse.payload;

import lombok.Data;

@Data
public class InputDto {

    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;

}
