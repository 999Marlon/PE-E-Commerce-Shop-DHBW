package com.ecommerce.domain.repository;

import com.ecommerce.domain.entities.Cart;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository{
    Optional<Cart> findByUserId(UUID userId);
    Optional<Cart> findById(UUID id);
    Cart save(Cart cart);
    void deleteById(UUID uuid);

}
