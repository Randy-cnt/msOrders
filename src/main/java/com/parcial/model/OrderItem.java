package com.parcial.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId; // Referencia al producto (de msProducts)
    private String productName;
    private Double price;
    private Integer quantity;

    @JsonIgnore // Evitar recursión infinita al serializar
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
