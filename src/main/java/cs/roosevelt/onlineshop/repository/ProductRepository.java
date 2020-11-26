package cs.roosevelt.onlineshop.repository;

import cs.roosevelt.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }