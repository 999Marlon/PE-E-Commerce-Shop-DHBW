package com.ecommerce.domain.dto;

import com.ecommerce.domain.valueobjects.Email;

public class AuthRequestDTO {
    private Email email;
    private String password;

    public AuthRequestDTO() {}

    public AuthRequestDTO(Email email, String password) {
        this.email = email;
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
