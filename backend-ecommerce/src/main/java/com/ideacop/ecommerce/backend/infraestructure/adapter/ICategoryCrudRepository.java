package com.ideacop.ecommerce.backend.infraestructure.adapter;

import com.ideacop.ecommerce.backend.infraestructure.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryCrudRepository extends CrudRepository<CategoryEntity, Integer> {
}
