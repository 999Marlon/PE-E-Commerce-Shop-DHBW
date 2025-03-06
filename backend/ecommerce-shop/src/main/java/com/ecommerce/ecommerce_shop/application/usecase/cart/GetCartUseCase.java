package com.ecommerce.ecommerce_shop.application.usecase.cart;

import com.ecommerce.ecommerce_shop.domain.repository.CartRepository;
import com.ecommerce.ecommerce_shop.interfaces.dto.CartDTO;
import com.ecommerce.ecommerce_shop.interfaces.mappers.CartMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetCartUseCase {

    private final CartRepository cartRepository;

    public GetCartUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartDTO execute(UUID userId) {
        return cartRepository.findByUserId(userId)
                .map(CartMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
    }
}

