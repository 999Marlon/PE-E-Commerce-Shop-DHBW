package com.ecommerce.ecommerce_shop.application.usecase.product;

import com.ecommerce.ecommerce_shop.domain.entities.Product;
import com.ecommerce.ecommerce_shop.domain.service.ProductService;
import com.ecommerce.ecommerce_shop.interfaces.dto.ProductDTO;
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

