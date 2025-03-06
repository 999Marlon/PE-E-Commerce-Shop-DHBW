package com.ecommerce.ecommerce_shop.infrastructure.persistence;

import com.ecommerce.ecommerce_shop.domain.entities.Order;
import com.ecommerce.ecommerce_shop.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, UUID>, OrderRepository {
}

