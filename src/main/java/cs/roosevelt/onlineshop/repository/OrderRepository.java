package cs.roosevelt.onlineshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cs.roosevelt.onlineshop.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
    Optional<Order> findById(String id);
}
