package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsProductByNameAndCategory_Id(String name, Integer category_id);

    @Query(value = "select max(id) from product", nativeQuery = true)
    Integer maxId();
}
