package com.ecommerce.domain.dto;

import java.util.UUID;

import com.ecommerce.domain.valueobjects.Email;

public class UserDTO {

    private UUID id;
    private String username;
    private Email email;
    
    public UserDTO(UUID id, String username, Email email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Email getEmail() {
        return email;
    }
    public void setEmail(Email email) {
        this.email = email;
    }
    
}
