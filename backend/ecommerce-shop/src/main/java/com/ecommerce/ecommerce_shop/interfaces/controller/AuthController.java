package com.ecommerce.ecommerce_shop.interfaces.controller;

import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce_shop.domain.model.User;
import com.ecommerce.ecommerce_shop.domain.service.AuthService;
import com.ecommerce.ecommerce_shop.domain.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthService authService;
    private final UserService userService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        return authService.login(email, password);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

}
