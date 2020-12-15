package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.model.Category;
import cs.roosevelt.onlineshop.model.Order;
import cs.roosevelt.onlineshop.model.OrderDetail;
import cs.roosevelt.onlineshop.model.Order;
import cs.roosevelt.onlineshop.model.User;
import cs.roosevelt.onlineshop.repository.CategoryRepository;
import cs.roosevelt.onlineshop.repository.OrderDetailsRepository;
import cs.roosevelt.onlineshop.repository.OrderRepository;
import cs.roosevelt.onlineshop.repository.UserRepository;
import cs.roosevelt.onlineshop.service.OrderService;
import cs.roosevelt.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * OrderServiceImpl defines the method signatures provided
 * by the OrderService interface for the controller to use.
 * <p>
 * Within each method, it uses methods from
 * the OrderRepository.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderDetailsRepository orderDetailRepository;  

    @Autowired
    private UserRepository userRepository;

	@Override
	public ResponseEntity<List<Order>> getAll(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Optional<Order>> getOne(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Order>> getAllByCategory(Integer categoryType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Order>> getAllContainingSearchString(String searchStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> save(Order orderToSave, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> update(Order orderToUpdate, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> delete(String orderId, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}
}