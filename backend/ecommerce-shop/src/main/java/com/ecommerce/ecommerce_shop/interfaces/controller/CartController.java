package com.ecommerce.ecommerce_shop.interfaces.controller;

import com.ecommerce.ecommerce_shop.domain.service.CartService;
import com.ecommerce.ecommerce_shop.interfaces.dto.CartDTO;

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
    public CartDTO addToCart(@PathVariable UUID userId, @PathVariable UUID productId) {
        return cartService.addToCart(userId, productId);
    }

    @GetMapping("/{userId}")
    public CartDTO getCart(@PathVariable UUID userId) {
        return cartService.getCartByUserId(userId);
    }


    @DeleteMapping("/{userId}/remove/{productId}")
    public CartDTO removeFromCart(@PathVariable UUID userId, @PathVariable UUID productId) {
        return cartService.removeFromCart(userId, productId);
    }

    @PutMapping("/{userId}/update-quantity/{productId}")
    public CartDTO updateProductQuantity(@PathVariable UUID userId, @PathVariable UUID productId, @RequestParam int quantity) {
        return cartService.updateProductQuantity(userId, productId, quantity);
    }
}
