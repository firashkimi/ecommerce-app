package com.ideacop.ecommerce.backend.infraestructure.mapper;

import com.ideacop.ecommerce.backend.domain.model.Order;
import com.ideacop.ecommerce.backend.infraestructure.entity.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {OrderProductMapper.class})
public interface OrderMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "orderProductEntities", target = "orderProducts"),
            @Mapping(source = "orderState", target = "orderState"),
            @Mapping(source = "userEntity.id", target = "userId"),
    })
    Order toOrder(OrderEntity orderEntity);
    Iterable<Order> toOrderList(Iterable<OrderEntity> orderEntities);

    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);
}
