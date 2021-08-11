package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.InputProduct;

import java.util.Date;
import java.util.List;

public interface InputProductRepository extends JpaRepository<InputProduct , Integer> {

    List<InputProduct> findAllByInputId(Integer input_id);
    List<InputProduct> findAllByExpireDateBeforeAndActive(Date expireDate, boolean active);
}
