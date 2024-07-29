package com.ideacop.ecommerce.backend.infraestructure.adapter;

import com.ideacop.ecommerce.backend.infraestructure.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
}
