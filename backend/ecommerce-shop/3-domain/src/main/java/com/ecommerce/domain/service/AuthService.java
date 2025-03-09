package com.ecommerce.domain.service;

import com.ecommerce.domain.security.JwtProvider;
import com.ecommerce.domain.valueobjects.Email;
import com.ecommerce.domain.dto.LoginResult;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.exceptions.InvalidCredentialsException;
import com.ecommerce.domain.exceptions.UserNotFoundException;
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

    public LoginResult login(Email email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Benutzer nicht gefunden."));

        if (!user.getPassword().equals(password) || !user.getEmail().toString().equals(email.toString())) {
            throw new InvalidCredentialsException("Falsche Anmeldedaten. Überprüfen Sie ihr Passwort oder Email Adresse.");
        }

        String token = jwtProvider.generateToken(user.getId().toString()); 
        return new LoginResult(user, token);
    }
}
