package com.ideacop.ecommerce.backend.domain.port;

import com.ideacop.ecommerce.backend.domain.model.Order;

public interface IOrderRepository {
    Order save(Order order);
    Order findById(Integer id);
    Iterable<Order> findAll();
    Iterable<Order> findByUserId(Integer userId);
    void updateStateById(Integer id, String state);
}
