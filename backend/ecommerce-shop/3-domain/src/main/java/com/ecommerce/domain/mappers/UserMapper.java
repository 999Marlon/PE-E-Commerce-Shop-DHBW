package com.ecommerce.domain.mappers;

import com.ecommerce.domain.dto.RegisterUserDTO;
import com.ecommerce.domain.dto.UserDTO;
import com.ecommerce.domain.entities.User;

public class UserMapper {

    private UserMapper() {}

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    public static User toEntity(RegisterUserDTO registerUserDTO) {
        return new User(registerUserDTO.getUsername(), registerUserDTO.getEmail(), registerUserDTO.getPassword());
    }
    
}
