package com.ecommerce.application.usecase.auth;

import org.springframework.stereotype.Component;

import com.ecommerce.domain.dto.AuthRequestDTO;
import com.ecommerce.domain.dto.AuthResponseDTO;
import com.ecommerce.domain.mappers.AuthMapper;
import com.ecommerce.domain.service.AuthService;

@Component
public class LoginUseCase {

    private final AuthService authService;

    public LoginUseCase(AuthService authService) {
        this.authService = authService;
    }

    public AuthResponseDTO execute(AuthRequestDTO authRequestDTO) {
        var loginResult = authService.login(authRequestDTO.getEmail(), authRequestDTO.getPassword());
        return AuthMapper.toDTO(loginResult.getUser(), loginResult.getToken());
    }
}
