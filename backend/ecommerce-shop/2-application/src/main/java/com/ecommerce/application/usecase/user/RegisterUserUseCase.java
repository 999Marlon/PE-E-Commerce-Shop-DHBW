package com.ecommerce.application.usecase.user;

import com.ecommerce.domain.service.UserService;
import com.ecommerce.domain.dto.RegisterUserDTO;
import com.ecommerce.domain.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserUseCase {

    private final UserService userService;

    public RegisterUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public UserDTO execute(RegisterUserDTO registerUserDTO) {
        return userService.registerUser(registerUserDTO);

    }
}
