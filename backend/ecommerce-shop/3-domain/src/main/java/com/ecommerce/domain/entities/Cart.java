package com.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private User user;

    @ManyToMany
    private List<Product> products = new ArrayList<>(); 

    public Cart() {
        this.products = new ArrayList<>();  
    }

    public Cart(User user, List<Product> products) {
        this.user = user;
        this.products = products != null ? products : new ArrayList<>();
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }


}
