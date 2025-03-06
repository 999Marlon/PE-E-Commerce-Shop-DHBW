package com.ecommerce.ecommerce_shop.application.usecase.auth;

import com.ecommerce.ecommerce_shop.domain.service.AuthService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginUseCase {

    private final AuthService authService;

    public LoginUseCase(AuthService authService) {
        this.authService = authService;
    }

    public Map<String, String> execute(String email, String password) {
        return authService.login(email, password);
    }
}
