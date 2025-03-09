package com.ecommerce.domain.dto;

import java.util.UUID;

public class DeleteUserDTO {
    private UUID userId;

    public DeleteUserDTO() {}

    public DeleteUserDTO(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
