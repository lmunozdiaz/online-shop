package cs.roosevelt.onlineshop.repository;

import cs.roosevelt.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findById(String id);
    
    Product findByName(String name);


    List<Product> findAllByCategoryType(Integer categoryType);

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameSearch,
                                                                                  String descriptionSearch);

}
