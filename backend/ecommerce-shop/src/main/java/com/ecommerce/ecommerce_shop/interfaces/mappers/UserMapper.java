package com.ecommerce.ecommerce_shop.interfaces.mappers;

import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.interfaces.dto.UserDTO;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
    
}
