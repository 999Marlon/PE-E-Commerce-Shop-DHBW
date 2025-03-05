package com.ecommerce.ecommerce_shop.interfaces.controller;

import com.ecommerce.ecommerce_shop.domain.model.Cart;
import com.ecommerce.ecommerce_shop.domain.service.CartService;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{userId}/add/{productId}")
    public Cart addToCart(@PathVariable UUID userId, @PathVariable UUID productId) {
        return cartService.addToCart(userId, productId);
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable UUID userId) {
        return cartService.getCartByUserId(userId);
    }


    @DeleteMapping("/{userId}/remove/{productId}")
    public Cart removeFromCart(@PathVariable UUID userId, @PathVariable UUID productId) {
        return cartService.removeFromCart(userId, productId);
    }

    @PutMapping("/{userId}/update-quantity/{productId}")
    public Cart updateProductQuantity(@PathVariable UUID userId, @PathVariable UUID productId, @RequestParam int quantity) {
        return cartService.updateProductQuantity(userId, productId, quantity);
    }
}
