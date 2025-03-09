package com.ecommerce.application.usecase.product;

import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.service.ProductService;
import com.ecommerce.domain.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateProductUseCase {

    private final ProductService productService;

    public UpdateProductUseCase(ProductService productService) {
        this.productService = productService;
    }

    public ProductDTO execute(UUID productId, Product updatedProduct) {
        return productService.updateProduct(productId, updatedProduct);
    }
}
