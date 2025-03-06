package com.ecommerce.ecommerce_shop.infrastructure.persistence;


import com.ecommerce.ecommerce_shop.domain.entities.Cart;
import com.ecommerce.ecommerce_shop.domain.repository.CartRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaCartRepository extends JpaRepository<Cart, UUID>, CartRepository {
}
