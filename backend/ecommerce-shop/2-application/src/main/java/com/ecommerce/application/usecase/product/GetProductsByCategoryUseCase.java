package com.ecommerce.application.usecase.product;

import com.ecommerce.domain.service.ProductService;
import com.ecommerce.domain.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductsByCategoryUseCase {

    private final ProductService productService;

    public GetProductsByCategoryUseCase(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductDTO> execute(String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }
}

