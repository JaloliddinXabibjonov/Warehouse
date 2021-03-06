package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.pdp.warehouse.entity.OutputProduct;

import java.util.List;

public interface OutputProductRepository extends JpaRepository<OutputProduct , Integer> {

    List<OutputProduct> findAllByOutputId(Integer output_id);
}
