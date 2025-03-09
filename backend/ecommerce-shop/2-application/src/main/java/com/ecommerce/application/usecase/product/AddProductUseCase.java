package com.ecommerce.application.usecase.product;

import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.service.ProductService;
import com.ecommerce.domain.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class AddProductUseCase {

    private final ProductService productService;

    public AddProductUseCase(ProductService productService) {
        this.productService = productService;
    }

    public ProductDTO execute(Product product) {
        return productService.addProduct(product);
    }
}

