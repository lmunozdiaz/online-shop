package cs.roosevelt.onlineshop.repository;

import cs.roosevelt.onlineshop.model.CartItem;
import cs.roosevelt.onlineshop.model.Product;
import cs.roosevelt.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUser(User user);

    CartItem findByProductAndUser(Product product, User user);

    Boolean existsByProductAndUser(Product product, User user);

    Boolean existsByProductIdAndUser(String productId, User user);

    Boolean deleteByProductIdAndUser(String productId, User user);

}
