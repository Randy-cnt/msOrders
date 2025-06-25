package com.parcial.repository;

import com.parcial.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientContainingIgnoreCase(String client);
    List<Order> findByStatusIgnoreCase(String status);
    List<Order> findByClientContainingIgnoreCaseAndStatusIgnoreCase(String client, String status);

}
