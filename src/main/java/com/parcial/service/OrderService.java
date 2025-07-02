package com.parcial.service;

import com.parcial.client.ProductClient;
import com.parcial.dto.ProductDTO;
import com.parcial.model.Order;
import com.parcial.model.OrderItem;
import com.parcial.repository.OrderRepository;
//import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductClient productClient;

    public Order save(Order order) {
        order.setDate(LocalDateTime.now());

        double total = 0;

        for (OrderItem item : order.getItems()) {
            // Llamar al microservicio de productos usando Circuit Breaker
            ProductDTO product = productClient.getProductById(item.getProductId());

            // Verificar si el producto vino vacío (por fallback)
            if (product == null || product.getStock() == 0) {
                throw new RuntimeException("No se pudo validar el stock del producto con id: " + item.getProductId());
            }

            // Verificar stock disponible
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + product.getName());
            }

            // Asignar precio al ítem
            item.setPrice(product.getPrice());

            // Sumar al total
            total += product.getPrice() * item.getQuantity();

            // Relación bidireccional
            item.setOrder(order);
        }

        order.setTotal(total);

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
