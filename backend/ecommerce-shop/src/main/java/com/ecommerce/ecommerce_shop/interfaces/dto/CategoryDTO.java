package com.ecommerce.ecommerce_shop.interfaces.dto;

import java.util.UUID;

public class CategoryDTO {

    private UUID id;
    private String name;
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
