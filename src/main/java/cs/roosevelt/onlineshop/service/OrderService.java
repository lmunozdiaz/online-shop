package cs.roosevelt.onlineshop.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import cs.roosevelt.onlineshop.model.Order;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface OrderService {

    ResponseEntity<List<Order>> getAll(HttpSession session);

    ResponseEntity<Optional<Order>> getOne(String orderId);

    ResponseEntity<List<Order>> getAllByCategory(Integer categoryType);

    ResponseEntity<List<Order>> getAllContainingSearchString(String searchStr);

    public ResponseEntity<String>  save(Order orderToSave, HttpSession session);

    ResponseEntity<String> update(Order orderToUpdate, HttpSession session);

    ResponseEntity<String> delete(String orderId, HttpSession session);

}
