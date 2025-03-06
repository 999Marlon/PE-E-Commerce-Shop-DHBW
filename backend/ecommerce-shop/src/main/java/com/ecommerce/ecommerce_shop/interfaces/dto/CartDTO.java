package com.ecommerce.ecommerce_shop.interfaces.dto;

import java.util.List;
import java.util.UUID;

public class CartDTO {

    private UUID id;
    private UUID userId;
    private List<UUID> productIds;
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public List<UUID> getProductIds() {
        return productIds;
    }
    public void setProductIds(List<UUID> productIds) {
        this.productIds = productIds;
    }
    
}
