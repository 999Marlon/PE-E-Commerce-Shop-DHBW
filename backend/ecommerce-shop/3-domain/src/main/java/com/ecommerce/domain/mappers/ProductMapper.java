package com.ecommerce.domain.mappers;

import com.ecommerce.domain.dto.ProductDTO;
import com.ecommerce.domain.entities.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl()); 
        dto.setCategoryId(product.getCategory().getId());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImageUrl()); 
        return product;
    }
    
}
