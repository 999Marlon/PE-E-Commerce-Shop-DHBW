package com.ecommerce.ecommerce_shop.application.usecase.cart;

import com.ecommerce.ecommerce_shop.domain.entities.Cart;
import com.ecommerce.ecommerce_shop.domain.entities.Product;
import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.domain.repository.CartRepository;
import com.ecommerce.ecommerce_shop.domain.repository.ProductRepository;
import com.ecommerce.ecommerce_shop.domain.repository.UserRepository;
import com.ecommerce.ecommerce_shop.interfaces.dto.CartDTO;
import com.ecommerce.ecommerce_shop.interfaces.mappers.CartMapper;
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

