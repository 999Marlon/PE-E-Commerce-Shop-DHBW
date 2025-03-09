package com.ecommerce.application.usecase.cart;

import com.ecommerce.domain.entities.Cart;
import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.repository.CartRepository;
import com.ecommerce.domain.repository.ProductRepository;
import com.ecommerce.domain.repository.UserRepository;
import com.ecommerce.domain.dto.CartDTO;
import com.ecommerce.domain.mappers.CartMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class AddProductToCartUseCase {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public AddProductToCartUseCase(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public CartDTO execute(UUID userId, UUID productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (cart.getProducts() == null) {
            cart.setProducts(new ArrayList<>());
        }

        cart.getProducts().add(product);
        return CartMapper.toDTO(cartRepository.save(cart));
    }
}

