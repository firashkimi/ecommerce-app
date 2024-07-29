package com.ideacop.ecommerce.backend.domain.port;

import com.ideacop.ecommerce.backend.domain.model.User;
import com.ideacop.ecommerce.backend.domain.model.UserDto;

public interface IUserRepository {
    User save(User user);
    User updateProfile(UserDto user);
    User findByEmail(String email);
    User findById(Integer id);
    Iterable<User> findAll();
    void deleteUser(Integer id);
}
