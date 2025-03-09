package com.ecommerce.domain.dto;

import java.util.UUID;

public class ProductDTO {

    private UUID id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;  
    private UUID categoryId;
    
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {this.price = price;}

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public UUID getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
    
}
