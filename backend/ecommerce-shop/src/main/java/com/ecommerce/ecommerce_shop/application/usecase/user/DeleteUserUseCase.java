package com.ecommerce.ecommerce_shop.application.usecase.user;

import com.ecommerce.ecommerce_shop.domain.service.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteUserUseCase {

    private final UserService userService;

    public DeleteUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public void execute(UUID id) {
        userService.deleteUser(id);
    }
}

