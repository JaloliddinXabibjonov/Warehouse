package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "select max(id) from users", nativeQuery = true)
    Integer maxId();
}
