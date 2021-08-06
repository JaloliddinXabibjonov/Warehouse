package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.Client;

public interface ClientRepository  extends JpaRepository<Client, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
