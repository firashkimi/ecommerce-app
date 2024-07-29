package com.ideacop.ecommerce.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String cellphone;
    private UserType userType;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
