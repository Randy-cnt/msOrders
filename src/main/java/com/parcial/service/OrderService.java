package com.parcial.service;

import com.parcial.model.Order;
import com.parcial.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order save(Order order) {
        order.setDate(LocalDateTime.now());

        // Calcular total (suma precio * cantidad de cada item)
        double total = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotal(total);

        // Establecer relación bidireccional
        order.getItems().forEach(item -> item.setOrder(order));

        return repository.save(order);
    }

    public List<Order> findOrders(String client, String status) {
    if (client != null && status != null) {
        return repository.findByClientContainingIgnoreCaseAndStatusIgnoreCase(client, status);
    } else if (client != null) {
        return repository.findByClientContainingIgnoreCase(client);
    } else if (status != null) {
        return repository.findByStatusIgnoreCase(status);
    } else {
        return repository.findAll();
    }
}
}
