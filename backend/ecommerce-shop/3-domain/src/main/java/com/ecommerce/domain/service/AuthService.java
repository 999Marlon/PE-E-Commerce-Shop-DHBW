package com.ecommerce.domain.service;

import com.ecommerce.domain.security.JwtProvider;
import com.ecommerce.domain.dto.LoginResult;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, JwtProvider jwtProvider){
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public LoginResult login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtProvider.generateToken(user.getId().toString()); 
        return new LoginResult(user, token);
    }
}
