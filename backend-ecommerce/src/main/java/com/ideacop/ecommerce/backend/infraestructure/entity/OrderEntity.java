package com.ideacop.ecommerce.backend.infraestructure.entity;

import com.ideacop.ecommerce.backend.domain.model.OrderProduct;
import com.ideacop.ecommerce.backend.domain.model.OrderState;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.PERSIST)
    private List<OrderProductEntity> orderProductEntities;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @ManyToOne
    private UserEntity userEntity;
}
