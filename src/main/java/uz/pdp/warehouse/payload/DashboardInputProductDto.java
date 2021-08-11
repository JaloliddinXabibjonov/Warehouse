package uz.pdp.warehouse.payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardInputProductDto {
    private Integer howManyTimes;
    private String name;
    private Double amount;
    private Double price;
}
