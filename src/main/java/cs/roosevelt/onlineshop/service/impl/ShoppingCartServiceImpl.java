package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.model.CartItem;
import cs.roosevelt.onlineshop.model.Product;
import cs.roosevelt.onlineshop.model.User;
import cs.roosevelt.onlineshop.repository.CartItemRepository;
import cs.roosevelt.onlineshop.repository.ProductRepository;
import cs.roosevelt.onlineshop.repository.UserRepository;
import cs.roosevelt.onlineshop.service.ProductService;
import cs.roosevelt.onlineshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<CartItem>> getCart(HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, they're valid

                // is the session user registered or a manager?
                if (sessionUser.getRole().equals("ROLE_CUSTOMER") || sessionUser.getRole().equals("ROLE_MANAGER")) {

                    // yes, they are; proceed with request

                    // find the cart items
                    List<CartItem> cartItems = cartItemRepository.findByUser(sessionUser);

                    // did we find any cart items for the user?
                    if (cartItems != null) {

                        // yes we did
                        return new ResponseEntity<>(cartItems, HttpStatus.OK);

                    } else {

                        //no we didn't find any
                        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

                    }

                } else {

                    // no, they're neither registered nor an employee; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }

    }

    @Override
    public ResponseEntity<List<CartItem>> saveItem(CartItem cartItemToSave, HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                System.out.println("The session user is: " + sessionUser.toString());

                // yes, they're valid

                // is the session user registered or a manager?
                if (sessionUser.getRole().equals("ROLE_CUSTOMER") || sessionUser.getRole().equals("ROLE_MANAGER")) {

                    // yes, they are; save the cart item and return OK status

                    // is the cart item user the same as the session user?
                    if (cartItemToSave.getUser().equals(sessionUser)) {

                        // yes, they're the same

                        CartItem existingCartItem;

                        // is the item already in the cart?
                        if (cartItemRepository.existsByProductAndUser(cartItemToSave.getProduct(), sessionUser)) {
                            // yes the item exists
                            existingCartItem = cartItemRepository
                                    .findByProductAndUser(cartItemToSave.getProduct(), sessionUser);

                            // decrease the product's stock quantity first, since the cart item is a promise

                            // get the product
                            Optional<Product> existingProduct = productRepository.findById(cartItemToSave
                                    .getProduct()
                                    .getId());

                            // does the product exist?
                            if (existingProduct.isPresent()) {
                                // save the product's new stock value based on the cart item's demands
                                int productStock = existingProduct.get().getStock() - cartItemToSave.getQuantity();

                                // is the product stock 0?
                                if (productStock == 0) {
                                    // set the product status to 1 (out of stock)
                                    existingProduct.get().setStatus(1);
                                } else if (productStock < 0) {
                                    // there's not enough in stock for the cartItem's demands
                                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                                }

                                // set the product's new stock quantity
                                existingProduct.get().setStock(productStock);
                            }

                            // set the new quantity
                            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemToSave.getQuantity());

                            // update the existing cart item's quantity
                            cartItemRepository.save(existingCartItem);

                            // update the existing product's stock quantity
                            productRepository.save(existingProduct.get());

                            // get a return list of the updated cart items
                            List<CartItem> cartItems = cartItemRepository.findByUser(sessionUser);

                            // return the modified cart item and OK status
                            return new ResponseEntity<>(cartItems, HttpStatus.OK);
                        } else {

                            // no it doesn't exist

                            // decrease the product's stock quantity first, since the cart item is a promise

                            // get the product
                            Optional<Product> existingProduct = productRepository.findById(cartItemToSave
                                    .getProduct()
                                    .getId());

                            // does the product exist?
                            if (existingProduct.isPresent()) {
                                // save the product's new stock value based on the cart item's demands
                                int productStock = existingProduct.get().getStock() - cartItemToSave.getQuantity();

                                // is the product stock 0?
                                if (productStock == 0) {
                                    // set the product status to 1 (out of stock)
                                    existingProduct.get().setStatus(1);
                                } else if (productStock < 0) {
                                    // there's not enough in stock for the cartItem's demands
                                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                                }

                                // set the product's new stock quantity
                                existingProduct.get().setStock(productStock);
                            }

                            // save the new cart item
                            cartItemRepository.save(cartItemToSave);

                            // update the existing product's stock quantity
                            productRepository.save(existingProduct.get());

                            // get a return list of the updated cart items
                            List<CartItem> cartItems = cartItemRepository.findByUser(sessionUser);

                            // return the saved item and OK status
                            return new ResponseEntity<>(cartItems, HttpStatus.OK);
                        }
                    } else {

                        // users don't match
                        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

                    }

                } else {

                    // no, they're neither registered nor a manager; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }

    }

    @Override
    public ResponseEntity<Boolean> removeItem(String productId, HttpSession session) {
        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, they're valid

                // is the session user registered or a manager?
                if (sessionUser.getRole().equals("ROLE_CUSTOMER") || sessionUser.getRole().equals("ROLE_MANAGER")) {

                    // yes, they are; remove the cart item and return OK status

                    // does the user's product exist?
                    if (cartItemRepository.existsByProductIdAndUser(productId, sessionUser)) {
                        // yes it exists; delete it
                        boolean isRemoved = cartItemRepository.deleteByProductIdAndUser(productId, sessionUser);

                        if (isRemoved) {
                            // item was deleted
                            return new ResponseEntity<>(true, HttpStatus.OK);
                        } else {
                            // item was not deleted
                            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
                        }
                    } else {
                        // it doesn't exist; return not found status
                        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
                    }

                } else {

                    // no, they're neither registered nor an employee; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }
    }

    @Override
    public ResponseEntity<Integer> getTotalQuantity(HttpSession session) {
        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                System.out.println("The session user is: " + sessionUser.toString());

                // yes, they're valid

                // is the session user registered or a manager?
                if (sessionUser.getRole().equals("ROLE_CUSTOMER") || sessionUser.getRole().equals("ROLE_MANAGER")) {

                    // yes, they are

                    // get all of the user's cart items
                    List<CartItem> existingCartItems = cartItemRepository.findByUser(sessionUser);

                    // to hold the total quantity
                    int totalQuantity = 0;
                    // is there any items?
                    if (existingCartItems != null) {
                        // yes, there are items; sum the total quantity
                        for (CartItem tempCartItem: existingCartItems) {
                            totalQuantity += tempCartItem.getQuantity();
                        }
                        // return the total quantity and OK status
                        return new ResponseEntity<>(totalQuantity, HttpStatus.OK);
                    } else {
                        // there were no items; return 0 quantity and Not Found status
                        return new ResponseEntity<>(totalQuantity, HttpStatus.NOT_FOUND);
                    }

                } else {

                    // no, they're neither registered nor a manager; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }
    }

}
