package com.ecommerce.application.usecase.product;

import com.ecommerce.domain.service.ProductService;
import com.ecommerce.domain.dto.ProductDTO;
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

