package com.ecommerce.ecommerce_shop.domain.service;

import com.ecommerce.ecommerce_shop.domain.entities.Cart;
import com.ecommerce.ecommerce_shop.domain.entities.Product;
import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.domain.repository.CartRepository;
import com.ecommerce.ecommerce_shop.domain.repository.ProductRepository;
import com.ecommerce.ecommerce_shop.domain.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart addToCart(UUID userId, UUID productId) {
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
        return cartRepository.save(cart);
    }
    
    public Cart getCartByUserId(UUID userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
    }

    public Cart removeFromCart(UUID userId, UUID productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

        cart.getProducts().removeIf(product -> product.getId().equals(productId));
        return cartRepository.save(cart);
    }

    public Cart updateProductQuantity(UUID userId, UUID productId, int quantity) {
        if (quantity < 1) {
            return removeFromCart(userId, productId);
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Füge die gewünschte Menge hinzu
        List<Product> updatedProducts = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            updatedProducts.add(product);
        }

        // Entferne das alte Produkt und füge die neue Menge ein
        cart.getProducts().removeIf(p -> p.getId().equals(productId));
        cart.getProducts().addAll(updatedProducts);

        return cartRepository.save(cart);
    }
    
}
