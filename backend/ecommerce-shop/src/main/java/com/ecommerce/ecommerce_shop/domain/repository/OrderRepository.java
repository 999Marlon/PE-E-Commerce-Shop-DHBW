package com.ecommerce.ecommerce_shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce_shop.domain.entities.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository{
    List<Order> findByUserId(UUID userId);
    Optional<Order> findById(UUID id);
    List<Order> findAll();
    Order save(Order order);
    void delete(Order order);
}
