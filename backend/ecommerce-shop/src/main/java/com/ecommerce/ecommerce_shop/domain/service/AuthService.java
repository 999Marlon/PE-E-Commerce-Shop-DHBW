package com.ecommerce.ecommerce_shop.domain.service;

import com.ecommerce.ecommerce_shop.config.JwtUtil;
import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public Map<String, String> login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getId().toString()); 
        return Map.of(
            "token", token,
            "userId", user.getId().toString()
        );
    }
}
