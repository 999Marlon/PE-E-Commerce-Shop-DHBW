package com.ecommerce.application.usecase.cart;

import com.ecommerce.domain.service.CartService;
import com.ecommerce.domain.dto.CartDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetCartUseCase {

    private final CartService cartService;

    public GetCartUseCase(CartService cartService) {
        this.cartService = cartService;
    }

    public CartDTO execute(UUID userId) {
        return cartService.getCartByUserId(userId);
    }
}

