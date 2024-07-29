package com.ideacop.ecommerce.backend.application;

import com.ideacop.ecommerce.backend.domain.model.User;
import com.ideacop.ecommerce.backend.domain.model.UserDto;
import com.ideacop.ecommerce.backend.domain.port.IUserRepository;

public class RegistrationService {
    private final IUserRepository iUserRepository;

    public RegistrationService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User register(User user) {
        return iUserRepository.save(user);
    }
    public User updateProfile(UserDto user) {
        return iUserRepository.updateProfile(user);
    }
}
