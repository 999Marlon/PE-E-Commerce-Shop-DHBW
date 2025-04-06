package com.ecommerce.domain.service;

import com.ecommerce.domain.entities.Cart;
import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.exceptions.CartNotFoundException;
import com.ecommerce.domain.exceptions.ProductNotFoundException;
import com.ecommerce.domain.exceptions.UserNotFoundException;
import com.ecommerce.domain.repository.CartRepository;
import com.ecommerce.domain.repository.ProductRepository;
import com.ecommerce.domain.repository.UserRepository;
import com.ecommerce.domain.dto.CartDTO;
import com.ecommerce.domain.mappers.CartMapper;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CartService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartQuantityService cartQuantityService;


    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository, CartQuantityService cartQuantityService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartQuantityService = cartQuantityService;

    }

    public CartDTO addToCart(UUID userId, UUID productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    
        if (cart.getProducts() == null) {
            cart.setProducts(new ArrayList<>());
        }
    
        cart.addProduct(product);

        return CartMapper.toDTO(cartRepository.save(cart));
    }
    
    public CartDTO getCartByUserId(UUID userId) {
        return cartRepository.findByUserId(userId)
                .map(CartMapper::toDTO)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + userId));
    }

    public CartDTO removeFromCart(UUID userId, UUID productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + userId));

        cart.removeProductById(productId);
        
        return CartMapper.toDTO(cartRepository.save(cart));
    }

    public CartDTO updateProductQuantity(UUID userId, UUID productId, int quantity) {
        if (quantity < 1) {
            return removeFromCart(userId, productId);
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        cartQuantityService.updateQuantity(cart, product, quantity);


        return CartMapper.toDTO(cartRepository.save(cart));
    }
    
}
