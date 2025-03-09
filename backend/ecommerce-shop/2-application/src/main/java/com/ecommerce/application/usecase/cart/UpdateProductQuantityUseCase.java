package com.ecommerce.application.usecase.cart;

import com.ecommerce.domain.entities.Cart;
import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.repository.CartRepository;
import com.ecommerce.domain.repository.ProductRepository;
import com.ecommerce.domain.dto.CartDTO;
import com.ecommerce.domain.mappers.CartMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UpdateProductQuantityUseCase {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public UpdateProductQuantityUseCase(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartDTO execute(UUID userId, UUID productId, int quantity) {
        if (quantity < 1) {
            return new RemoveProductFromCartUseCase(cartRepository).execute(userId, productId);
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<Product> updatedProducts = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            updatedProducts.add(product);
        }

        cart.getProducts().removeIf(p -> p.getId().equals(productId));
        cart.getProducts().addAll(updatedProducts);

        return CartMapper.toDTO(cartRepository.save(cart));
    }
}
