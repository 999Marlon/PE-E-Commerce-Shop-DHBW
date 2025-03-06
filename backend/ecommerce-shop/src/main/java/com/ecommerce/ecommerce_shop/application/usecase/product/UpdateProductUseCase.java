package com.ecommerce.ecommerce_shop.application.usecase.product;

import com.ecommerce.ecommerce_shop.domain.entities.Product;
import com.ecommerce.ecommerce_shop.domain.service.ProductService;
import com.ecommerce.ecommerce_shop.interfaces.dto.ProductDTO;
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
