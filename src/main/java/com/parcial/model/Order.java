package com.parcial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "el nombre del cliente es obligatorio")
    private String client;

    private LocalDateTime date;

    @NotBlank(message = "el estado del pedido es obligatorio")
    private String status;

    private Double total;

    @NotEmpty(message = "el pedido debe tener al menos un item")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
