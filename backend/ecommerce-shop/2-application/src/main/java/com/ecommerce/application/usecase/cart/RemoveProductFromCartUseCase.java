package com.ecommerce.application.usecase.cart;

import com.ecommerce.domain.service.CartService;
import com.ecommerce.domain.dto.CartDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RemoveProductFromCartUseCase {

    private final CartService cartService;

    public RemoveProductFromCartUseCase(CartService cartService) {
        this.cartService = cartService;
    }

    public CartDTO execute(UUID userId, UUID productId) {
        return cartService.removeFromCart(userId, productId);
    }
}

