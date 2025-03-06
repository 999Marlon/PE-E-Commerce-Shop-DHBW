package com.ecommerce.ecommerce_shop.application.usecase.product;

import com.ecommerce.ecommerce_shop.domain.service.ProductService;
import com.ecommerce.ecommerce_shop.interfaces.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductsUseCase {

    private final ProductService productService;

    public GetAllProductsUseCase(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductDTO> execute() {
        return productService.getAllProducts();
    }
}
