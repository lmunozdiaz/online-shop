package cs.roosevelt.onlineshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cs.roosevelt.onlineshop.model.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, String> {
    Optional<OrderDetail> findById(String id);
}
