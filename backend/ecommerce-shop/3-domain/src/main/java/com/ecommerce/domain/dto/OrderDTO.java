package com.ecommerce.domain.dto;

import java.util.List;
import java.util.UUID;

import com.ecommerce.domain.valueobjects.Address;

public class OrderDTO {

    private UUID id;
    private UUID userId;
    private Address address;
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

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    
}
