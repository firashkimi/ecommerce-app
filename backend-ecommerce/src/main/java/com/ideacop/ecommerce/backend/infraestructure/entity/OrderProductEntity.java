package com.ideacop.ecommerce.backend.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_products")
@Data
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Integer productId;

    @ManyToOne
    private OrderEntity orderEntity;
}
