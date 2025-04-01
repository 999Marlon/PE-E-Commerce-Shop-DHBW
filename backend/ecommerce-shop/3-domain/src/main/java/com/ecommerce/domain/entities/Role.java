package com.ecommerce.domain.entities;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    // Getter-Methode
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }
}
