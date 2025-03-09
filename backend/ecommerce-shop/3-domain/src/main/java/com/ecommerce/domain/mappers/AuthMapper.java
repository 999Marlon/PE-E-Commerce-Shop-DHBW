package com.ecommerce.domain.mappers;

import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.dto.AuthResponseDTO;

public class AuthMapper {
    public static AuthResponseDTO toDTO(User user, String token) {
        return new AuthResponseDTO(user.getId(), token);
    }
}

