package com.ecommerce.domain.service;

import com.ecommerce.domain.entities.Cart;
import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.repository.CartRepository;
import com.ecommerce.domain.repository.ProductRepository;
import com.ecommerce.domain.repository.UserRepository;
import com.ecommerce.domain.dto.CartDTO;
import com.ecommerce.domain.mappers.CartMapper;

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

    public CartDTO addToCart(UUID userId, UUID productId) {
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
    
    public CartDTO getCartByUserId(UUID userId) {
        return cartRepository.findByUserId(userId)
                .map(CartMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
    }

    public CartDTO removeFromCart(UUID userId, UUID productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

        cart.getProducts().removeIf(product -> product.getId().equals(productId));
        return CartMapper.toDTO(cartRepository.save(cart));
    }

    public CartDTO updateProductQuantity(UUID userId, UUID productId, int quantity) {
        if (quantity < 1) {
            return removeFromCart(userId, productId);
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
