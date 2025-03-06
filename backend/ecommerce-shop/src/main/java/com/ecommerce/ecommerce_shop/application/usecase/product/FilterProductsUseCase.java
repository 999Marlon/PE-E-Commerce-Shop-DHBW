package com.ecommerce.ecommerce_shop.application.usecase.product;

import com.ecommerce.ecommerce_shop.domain.service.ProductService;
import com.ecommerce.ecommerce_shop.interfaces.dto.ProductDTO;
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

