package com.ecommerce.ecommerce_shop.application.usecase.user;

import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.domain.service.UserService;
import com.ecommerce.ecommerce_shop.interfaces.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateUserUseCase {

    private final UserService userService;

    public UpdateUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public UserDTO execute(UUID id, User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
}

