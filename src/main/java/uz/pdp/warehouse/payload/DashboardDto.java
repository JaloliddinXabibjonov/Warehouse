package uz.pdp.warehouse.payload;
import lombok.Data;


import java.util.*;

@Data
public class DashboardDto {

    private List<DashboardInputProductDto> inputProductDtos;
    private List<DashboardOutputProductDto> outputProductDtos;
    private Integer productListWithExpireDate;
}