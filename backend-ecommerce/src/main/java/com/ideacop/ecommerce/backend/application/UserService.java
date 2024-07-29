package com.ideacop.ecommerce.backend.application;


import com.ideacop.ecommerce.backend.domain.model.User;
import com.ideacop.ecommerce.backend.domain.port.IUserRepository;



public class UserService {
    private final IUserRepository iUserRepository;


    public UserService(IUserRepository iUserRepository
    ) {
        this.iUserRepository = iUserRepository;
    }

    public User save(User user) {
        return iUserRepository.save(user);
    }

    public User findById(Integer id) {
        return iUserRepository.findById(id);
    }

    public User findByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }

    public Iterable<User> findAll(){
        return iUserRepository.findAll();
    }

    public void deleteUser(Integer id) { iUserRepository.deleteUser(id); }


}
