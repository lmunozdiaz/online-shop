package cs.roosevelt.onlineshop.service;

import cs.roosevelt.onlineshop.model.CartItem;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ShoppingCartService {

    ResponseEntity<List<CartItem>> getCart(HttpSession session);

    ResponseEntity<CartItem> saveItem(CartItem cartItemToSave, HttpSession session);

    ResponseEntity<Boolean> removeItem(String productId, HttpSession session);

}
