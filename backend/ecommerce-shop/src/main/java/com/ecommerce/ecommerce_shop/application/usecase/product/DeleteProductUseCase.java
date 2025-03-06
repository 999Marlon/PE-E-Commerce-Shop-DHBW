package com.ecommerce.ecommerce_shop.application.usecase.product;

import com.ecommerce.ecommerce_shop.domain.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteProductUseCase {

    private final ProductService productService;

    public DeleteProductUseCase(ProductService productService) {
        this.productService = productService;
    }

    public void execute(UUID productId) {
        productService.deleteProduct(productId);
    }
}

