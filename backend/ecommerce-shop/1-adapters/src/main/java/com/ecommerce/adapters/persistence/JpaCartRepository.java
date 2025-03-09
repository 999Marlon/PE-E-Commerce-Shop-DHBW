package com.ecommerce.adapters.persistence;


import com.ecommerce.domain.entities.Cart;
import com.ecommerce.domain.repository.CartRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCartRepository extends JpaRepository<Cart, UUID>, CartRepository {
    Optional<Cart> findByUserId(UUID userId);

}
