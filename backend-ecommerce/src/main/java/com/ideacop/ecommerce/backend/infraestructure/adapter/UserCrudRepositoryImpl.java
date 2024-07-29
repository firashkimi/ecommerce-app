package com.ideacop.ecommerce.backend.infraestructure.adapter;

import com.ideacop.ecommerce.backend.domain.model.User;
import com.ideacop.ecommerce.backend.domain.model.UserDto;
import com.ideacop.ecommerce.backend.domain.port.IUserRepository;
import com.ideacop.ecommerce.backend.infraestructure.mapper.UserDtoMapper;
import com.ideacop.ecommerce.backend.infraestructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserCrudRepositoryImpl implements IUserRepository {
    private final IUserCrudRepository iUserCrudRepository;
    private final UserMapper userMapper;
    private final UserDtoMapper userDtoMapper;

    public UserCrudRepositoryImpl(IUserCrudRepository iUserCrudRepository, UserMapper userMapper,
                                  UserDtoMapper userDtoMapper
    ) {
        this.iUserCrudRepository = iUserCrudRepository;
        this.userMapper = userMapper;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public User save(User user) {
        return userMapper.toUser(iUserCrudRepository.save(userMapper.toUserEntity(user)));
    }
@Override
    public User updateProfile(UserDto user) {
        return userMapper.toUser(iUserCrudRepository.save(userDtoMapper.toUserEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.toUser(iUserCrudRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Usuario con email: " + email + " no encontrado.")
        ));
    }

    @Override
    public User findById(Integer id) {
        return userMapper.toUser(iUserCrudRepository.findById(id).get());
    }

    @Override
    public Iterable<User> findAll() {
        return userMapper.toUsers(iUserCrudRepository.findAll());
    }

    @Override
    public void deleteUser(Integer id) {
        iUserCrudRepository.deleteById(id);
    }
}
