package com.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ecommerce.domain.valueobjects.Address;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "orders_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private List<Product> products = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private Address shippingAddress; 

    public Order() {}

    public Order(User user,Address shippingAddress, List<Product> products, OrderStatus status) {
        this.user = user;
        this.shippingAddress = shippingAddress;
        this.products = products;
        this.status = status;
    }

     public UUID getId() { return id; }
     public User getUser() { return user; }
     public List<Product> getProducts() { return products; }
     public OrderStatus getStatus() { return status; }
     public Address getShippingAddress() { return shippingAddress; }

     public void setId(UUID id) { this.id = id; }
     public void setUser(User user) { this.user = user; }
     public void setProducts(List<Product> products) { this.products = products; }
     public void setStatus(OrderStatus status) { this.status = status; }
     public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }

   

   

    
}

