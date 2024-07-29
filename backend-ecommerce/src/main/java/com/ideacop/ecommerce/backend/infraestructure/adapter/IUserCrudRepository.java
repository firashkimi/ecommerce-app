package com.ideacop.ecommerce.backend.infraestructure.adapter;

import com.ideacop.ecommerce.backend.infraestructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
