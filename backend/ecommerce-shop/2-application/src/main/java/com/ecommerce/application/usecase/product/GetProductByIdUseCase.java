package com.ecommerce.application.usecase.product;

import com.ecommerce.domain.service.ProductService;
import com.ecommerce.domain.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetProductByIdUseCase {

    private final ProductService productService;

    public GetProductByIdUseCase(ProductService productService) {
        this.productService = productService;
    }

    public ProductDTO execute(UUID productId) {
        return productService.getProductById(productId);
    }
}

