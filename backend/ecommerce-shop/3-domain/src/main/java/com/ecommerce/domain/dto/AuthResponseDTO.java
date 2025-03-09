package com.ecommerce.domain.dto;

import java.util.UUID;

public class AuthResponseDTO {
    private UUID userId;
    private String token;

    public AuthResponseDTO() {}

    public AuthResponseDTO(UUID userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
