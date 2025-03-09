package com.ecommerce.domain.dto;

import java.util.List;
import java.util.UUID;

public class OrderDTO {

    private UUID id;
    private UUID userId;
    private List<UUID> productIds;
    private String status;
    
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
