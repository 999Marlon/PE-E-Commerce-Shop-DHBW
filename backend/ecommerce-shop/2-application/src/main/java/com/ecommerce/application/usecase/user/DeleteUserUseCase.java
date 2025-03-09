package com.ecommerce.application.usecase.user;

import com.ecommerce.domain.dto.DeleteUserDTO;
import com.ecommerce.domain.mappers.UserMapper;
import com.ecommerce.domain.service.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteUserUseCase {

    private final UserService userService;

    public DeleteUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public DeleteUserDTO execute(UUID id) {
        userService.deleteUser(id);
        return UserMapper.toDeleteDTO(id);

    }
}

