package cs.roosevelt.onlineshop.controller;

import cs.roosevelt.onlineshop.model.CartItem;
import cs.roosevelt.onlineshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<CartItem>> fetchCartItems(HttpSession session) {
        return shoppingCartService.getCart(session);
    }

    @PostMapping(value = {"/add-to-cart", "/add-to-cart/"})
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItemToSave, HttpSession session) {
        return shoppingCartService.saveItem(cartItemToSave, session);
    }

    @DeleteMapping(value = {"/remove-from-cart/{id}", "/remove-from-cart/{id}"})
    public ResponseEntity<Boolean> removeCartItem(@PathVariable("id") String productId, HttpSession session) {
        return shoppingCartService.removeItem(productId, session);
    }

}
