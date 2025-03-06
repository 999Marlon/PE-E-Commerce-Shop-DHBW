package com.ecommerce.ecommerce_shop.application.usecase.product;

import com.ecommerce.ecommerce_shop.domain.service.ProductService;
import com.ecommerce.ecommerce_shop.interfaces.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchProductsUseCase {

    private final ProductService productService;

    public SearchProductsUseCase(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductDTO> execute(String query) {
        return productService.searchProducts(query);
    }
}

