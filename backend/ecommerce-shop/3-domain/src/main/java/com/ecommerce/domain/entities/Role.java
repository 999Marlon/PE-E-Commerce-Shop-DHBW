package com.ecommerce.domain.entities;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private final String systemRole;

    Role(String systemRole) {
        this.systemRole = systemRole;
    }

    public String getRole() {
        return systemRole;
    }

    @Override
    public String toString() {
        return systemRole;
    }
}
