package com.ecommerce.ecommerce_shop.domain.repository;

import com.ecommerce.ecommerce_shop.domain.entities.Cart;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository{
    Optional<Cart> findByUserId(UUID userId);
    Optional<Cart> findById(UUID id);
    Cart save(Cart cart);
    void deleteById(UUID uuid);

}
