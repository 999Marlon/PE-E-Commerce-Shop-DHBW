package com.ecommerce.domain.mappers;

import java.util.UUID;

import com.ecommerce.domain.dto.DeleteUserDTO;
import com.ecommerce.domain.dto.RegisterUserDTO;
import com.ecommerce.domain.dto.UserDTO;
import com.ecommerce.domain.entities.User;

public class UserMapper {

    private UserMapper() {}

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getAddress());
    }

    public static User toEntity(RegisterUserDTO registerUserDTO) {
        return new User(registerUserDTO.getUsername(), registerUserDTO.getEmail(), registerUserDTO.getPassword(), registerUserDTO.getAddress());
    }

    public static DeleteUserDTO toDeleteDTO(UUID userId) {
        return new DeleteUserDTO(userId);
    }
    
}
