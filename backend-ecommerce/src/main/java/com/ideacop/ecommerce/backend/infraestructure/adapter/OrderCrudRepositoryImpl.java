package com.ideacop.ecommerce.backend.infraestructure.adapter;

import com.ideacop.ecommerce.backend.domain.model.Order;
import com.ideacop.ecommerce.backend.domain.model.OrderState;
import com.ideacop.ecommerce.backend.domain.port.IOrderRepository;
import com.ideacop.ecommerce.backend.infraestructure.entity.OrderEntity;
import com.ideacop.ecommerce.backend.infraestructure.entity.UserEntity;
import com.ideacop.ecommerce.backend.infraestructure.mapper.OrderMapper;
import com.ideacop.ecommerce.backend.infraestructure.mapper.OrderProductMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository {
    private final IOrderCrudRepository iOrderCrudRepository;
    private final OrderMapper orderMapper;

    public OrderCrudRepositoryImpl(IOrderCrudRepository iOrderCrudRepository, OrderMapper orderMapper) {
        this.iOrderCrudRepository = iOrderCrudRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity= orderMapper.toOrderEntity(order);

        orderEntity.getOrderProductEntities().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );

        return orderMapper.toOrder(iOrderCrudRepository.save(orderEntity));
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.toOrder(iOrderCrudRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order con id: " + id + " no encontrada")));
    }

    @Override
    public Iterable<Order> findAll() {
        return orderMapper.toOrderList(iOrderCrudRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity= new UserEntity();
        userEntity.setId(userId);

        return orderMapper.toOrderList(iOrderCrudRepository.findByUserEntity(userEntity));
    }

    @Override
    public void updateStateById(Integer id, String state) {
        OrderState orderState = OrderState.valueOf(state);
        if(orderState.equals(OrderState.CONFIRMED)) {
            iOrderCrudRepository.updateStateById(id, OrderState.CONFIRMED);
        } else {
            iOrderCrudRepository.updateStateById(id, OrderState.CACELLED);
        }
    }
}
