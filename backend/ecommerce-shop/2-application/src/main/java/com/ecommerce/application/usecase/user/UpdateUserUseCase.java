package com.ecommerce.application.usecase.user;

import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.service.UserService;
import com.ecommerce.domain.dto.UserDTO;
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

