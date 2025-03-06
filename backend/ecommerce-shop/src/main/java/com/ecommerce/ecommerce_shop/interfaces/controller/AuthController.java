package com.ecommerce.ecommerce_shop.interfaces.controller;

import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce_shop.application.usecase.auth.LoginUseCase;
import com.ecommerce.ecommerce_shop.application.usecase.user.RegisterUserUseCase;
import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.interfaces.dto.UserDTO;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final LoginUseCase loginUseCase;
    private final RegisterUserUseCase registerUserUseCase;


    public AuthController(LoginUseCase loginUseCase, RegisterUserUseCase registerUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        return loginUseCase.execute(email, password);
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user) {
        return registerUserUseCase.execute(user);
    }

}
