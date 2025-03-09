package com.ecommerce.application.usecase.cart;

import com.ecommerce.domain.repository.CartRepository;
import com.ecommerce.domain.dto.CartDTO;
import com.ecommerce.domain.mappers.CartMapper;
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

