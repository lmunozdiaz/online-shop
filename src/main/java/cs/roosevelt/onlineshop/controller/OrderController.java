package cs.roosevelt.onlineshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cs.roosevelt.onlineshop.model.Order;
import cs.roosevelt.onlineshop.service.OrderService;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
    private OrderService orderService;

    /**
     * fetch-all-orders endpoint
     *
     * NOTE: Used only for debugging or admins; this
     * is not used in the angular project
     *
     * @param session The http session.
     * @return A list of all the orders.
     */
    @GetMapping(value = {"/admin/allOrders", "/admin/allOrders/"})
    public ResponseEntity<List<Order>> fetchAllOrders(HttpSession session) {
        return orderService.getAll(session);
    }
}