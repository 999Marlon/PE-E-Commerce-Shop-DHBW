package com.ecommerce.adapters.interfaces.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.application.usecase.auth.LoginUseCase;
import com.ecommerce.domain.dto.AuthRequestDTO;
import com.ecommerce.domain.dto.AuthResponseDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            AuthResponseDTO loginUser = loginUseCase.execute(authRequestDTO);
                return ResponseEntity.ok(loginUser);
        }
        catch(Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
