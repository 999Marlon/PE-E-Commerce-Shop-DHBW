package com.ecommerce.domain.repository;

import com.ecommerce.domain.entities.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository{
    List<Order> findByUserId(UUID userId);
    Optional<Order> findById(UUID id);
    List<Order> findAll();
    Order save(Order order);
    void deleteById(UUID uuid);
}
