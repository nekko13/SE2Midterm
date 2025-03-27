package fit.se2.SE2Midterm.repository;

import fit.se2.SE2Midterm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductType(String productType);
    List<Product> findByOriginalPriceIsNotNull();
}