package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier , Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
