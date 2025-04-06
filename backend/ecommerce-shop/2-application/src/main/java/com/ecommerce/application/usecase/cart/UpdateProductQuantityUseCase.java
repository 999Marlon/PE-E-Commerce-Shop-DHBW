package com.ecommerce.application.usecase.cart;
import com.ecommerce.domain.service.CartService;
import com.ecommerce.domain.dto.CartDTO;
import org.springframework.stereotype.Component;


import java.util.UUID;

@Component
public class UpdateProductQuantityUseCase {

    private final CartService cartService;

    public UpdateProductQuantityUseCase(CartService cartService) {
        this.cartService = cartService;
    }

    public CartDTO execute(UUID userId, UUID productId, int quantity) {
        return cartService.updateProductQuantity(userId, productId, quantity);
    }
}
