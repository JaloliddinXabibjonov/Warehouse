package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
