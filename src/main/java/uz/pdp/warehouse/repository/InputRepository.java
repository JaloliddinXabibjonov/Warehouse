package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
    @Query(value = "select max(id) from Input",nativeQuery = true)
        Integer maxId();
}
