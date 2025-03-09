package com.ecommerce.adapters.persistence;

import com.ecommerce.domain.entities.Order;
import com.ecommerce.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, UUID>, OrderRepository {
    List<Order> findByUserId(UUID userId);
}

