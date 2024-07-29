package com.ideacop.ecommerce.backend.infraestructure.mapper;

import com.ideacop.ecommerce.backend.domain.model.OrderProduct;
import com.ideacop.ecommerce.backend.infraestructure.entity.OrderProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "productId", target = "productId")
    })
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);
    Iterable<OrderProduct> orderProductList(Iterable<OrderProductEntity> orderProductEntities);

    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);
}
