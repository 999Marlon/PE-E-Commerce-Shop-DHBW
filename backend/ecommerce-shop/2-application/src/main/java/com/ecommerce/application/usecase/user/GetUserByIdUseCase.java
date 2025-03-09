package com.ecommerce.application.usecase.user;

import com.ecommerce.domain.service.UserService;
import com.ecommerce.domain.dto.UserDTO;
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

