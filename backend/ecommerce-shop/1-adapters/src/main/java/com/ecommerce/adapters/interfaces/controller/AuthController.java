package com.ecommerce.adapters.interfaces.controller;

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
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO) {
        return loginUseCase.execute(authRequestDTO);
    }


}
