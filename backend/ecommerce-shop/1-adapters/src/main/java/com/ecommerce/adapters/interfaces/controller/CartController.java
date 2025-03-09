package com.ecommerce.adapters.interfaces.controller;

import com.ecommerce.application.usecase.cart.AddProductToCartUseCase;
import com.ecommerce.application.usecase.cart.GetCartUseCase;
import com.ecommerce.application.usecase.cart.RemoveProductFromCartUseCase;
import com.ecommerce.application.usecase.cart.UpdateProductQuantityUseCase;
import com.ecommerce.domain.dto.CartDTO;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final AddProductToCartUseCase addProductToCartUseCase;
    private final GetCartUseCase getCartUseCase;
    private final RemoveProductFromCartUseCase removeProductFromCartUseCase;
    private final UpdateProductQuantityUseCase updateProductQuantityUseCase;

    public CartController(AddProductToCartUseCase addProductToCartUseCase,GetCartUseCase getCartUseCase,RemoveProductFromCartUseCase removeProductFromCartUseCase,UpdateProductQuantityUseCase updateProductQuantityUseCase) {
        this.addProductToCartUseCase = addProductToCartUseCase;
        this.getCartUseCase = getCartUseCase;
        this.removeProductFromCartUseCase = removeProductFromCartUseCase;
        this.updateProductQuantityUseCase = updateProductQuantityUseCase;
    }


    @PostMapping("/{userId}/add/{productId}")
    public CartDTO addToCart(@PathVariable UUID userId, @PathVariable UUID productId) {
        return addProductToCartUseCase.execute(userId, productId);
    }

    @GetMapping("/{userId}")
    public CartDTO getCart(@PathVariable UUID userId) {
        return getCartUseCase.execute(userId);
    }


    @DeleteMapping("/{userId}/remove/{productId}")
    public CartDTO removeFromCart(@PathVariable UUID userId, @PathVariable UUID productId) {
        return removeProductFromCartUseCase.execute(userId, productId);
    }

    @PutMapping("/{userId}/update-quantity/{productId}")
    public CartDTO updateProductQuantity(@PathVariable UUID userId, @PathVariable UUID productId, @RequestParam int quantity) {
        return updateProductQuantityUseCase.execute(userId, productId, quantity);
    }
}
