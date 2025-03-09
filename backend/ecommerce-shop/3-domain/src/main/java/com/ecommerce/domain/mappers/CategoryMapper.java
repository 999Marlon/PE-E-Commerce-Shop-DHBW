package com.ecommerce.domain.mappers;

import com.ecommerce.domain.dto.CategoryDTO;
import com.ecommerce.domain.entities.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
    
}
