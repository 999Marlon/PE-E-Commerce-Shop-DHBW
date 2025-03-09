package com.ecommerce.domain.dto;

import com.ecommerce.domain.entities.User;

public class LoginResult {
    private final User user;
    private final String token;

    public LoginResult(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}

