package com.ecommerce.ecommerce_shop.domain.model;

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
}
