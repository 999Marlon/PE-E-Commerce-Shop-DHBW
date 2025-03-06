package com.ecommerce.ecommerce_shop.application.usecase.user;

import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.domain.service.UserService;
import com.ecommerce.ecommerce_shop.interfaces.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserUseCase {

    private final UserService userService;

    public RegisterUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public UserDTO execute(User user) {
        return userService.registerUser(user);
    }
}
