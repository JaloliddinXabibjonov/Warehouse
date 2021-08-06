package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
    @Query(value = "select max(id) from Output",nativeQuery = true)
        Integer maxId();
}
