package com.ecommerce.ecommerce_shop.application.usecase.user;

import com.ecommerce.ecommerce_shop.domain.service.UserService;
import com.ecommerce.ecommerce_shop.interfaces.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetUserByIdUseCase {

    private final UserService userService;

    public GetUserByIdUseCase(UserService userService) {
        this.userService = userService;
    }

    public UserDTO execute(UUID id) {
        return userService.getUserById(id);
    }
}

