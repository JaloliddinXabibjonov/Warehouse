package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DashboardOutputProductDto {
    private Integer howManyTimes;
    private String name;
    private Double amount;
    private Double price;
}
