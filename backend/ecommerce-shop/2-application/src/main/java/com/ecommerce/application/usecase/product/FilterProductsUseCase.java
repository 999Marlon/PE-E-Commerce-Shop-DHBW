package com.ecommerce.application.usecase.product;

import com.ecommerce.domain.service.ProductService;
import com.ecommerce.domain.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterProductsUseCase {

    private final ProductService productService;

    public FilterProductsUseCase(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductDTO> execute(double minPrice, double maxPrice) {
        return productService.filterProducts(minPrice, maxPrice);
    }
}

