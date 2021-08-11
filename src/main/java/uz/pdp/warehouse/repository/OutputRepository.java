package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.Output;

import java.sql.Timestamp;
import java.util.List;

public interface OutputRepository extends JpaRepository<Output, Integer> {
    @Query(value = "select max(id) from Output",nativeQuery = true)
        Integer maxId();
    List<Output> findAllByDateBetween(Timestamp start, Timestamp end);
}
