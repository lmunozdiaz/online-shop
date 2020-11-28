package cs.roosevelt.onlineshop.repository;

import cs.roosevelt.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryId(Long id);

    List<Product> findByNameIgnoreCaseContaining(String keyword);

}