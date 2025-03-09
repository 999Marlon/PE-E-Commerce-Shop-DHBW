package com.ecommerce.domain.dto;

import com.ecommerce.domain.valueobjects.Email;

public class RegisterUserDTO {
    private String username;
    private Email email;
    private String password;

    public RegisterUserDTO(String username, Email email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Email getEmail() { return email; }
    public void setEmail(Email email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

