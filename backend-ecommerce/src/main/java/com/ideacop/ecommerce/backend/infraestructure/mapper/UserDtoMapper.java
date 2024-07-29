package com.ideacop.ecommerce.backend.infraestructure.mapper;

import com.ideacop.ecommerce.backend.domain.model.User;
import com.ideacop.ecommerce.backend.domain.model.UserDto;
import com.ideacop.ecommerce.backend.infraestructure.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "username", target = "username"),
                    @Mapping(source = "firstname", target = "firstname"),
                    @Mapping(source = "lastname", target = "lastname"),
                    @Mapping(source = "email", target = "email"),
                    @Mapping(source = "address", target = "address"),
                    @Mapping(source = "cellphone", target = "cellphone"),
                    @Mapping(source = "userType", target = "userType"),
                    @Mapping(source = "dateCreated", target = "dateCreated"),
                    @Mapping(source = "dateUpdated", target = "dateUpdated"),
            }
    )
    User toUser(UserEntity userEntity);

    Iterable<UserDto> toUsers(Iterable<UserEntity> userEntities);

    @InheritInverseConfiguration
    UserEntity toUserEntity(UserDto user);
}
