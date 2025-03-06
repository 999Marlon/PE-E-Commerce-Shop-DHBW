package com.ecommerce.ecommerce_shop.application.usecase.cart;

import com.ecommerce.ecommerce_shop.domain.entities.Cart;
import com.ecommerce.ecommerce_shop.domain.repository.CartRepository;
import com.ecommerce.ecommerce_shop.interfaces.dto.CartDTO;
import com.ecommerce.ecommerce_shop.interfaces.mappers.CartMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RemoveProductFromCartUseCase {

    private final CartRepository cartRepository;

    public RemoveProductFromCartUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartDTO execute(UUID userId, UUID productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

        cart.getProducts().removeIf(product -> product.getId().equals(productId));
        return CartMapper.toDTO(cartRepository.save(cart));
    }
}

