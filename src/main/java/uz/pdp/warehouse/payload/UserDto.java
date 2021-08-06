package uz.pdp.warehouse.payload;

import lombok.Data;
import uz.pdp.warehouse.entity.Warehouse;

import javax.persistence.Column;
import java.util.List;

@Data
public class UserDto {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String code;

    private String password;

    private List<Integer> warehouseList;
}
