package com.ecommerce.application.usecase.category;

import com.ecommerce.domain.service.CategoryService;
import com.ecommerce.domain.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class GetCategoryByNameUseCase {

    private final CategoryService categoryService;

    public GetCategoryByNameUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryDTO execute(String name) {
        return categoryService.getCategoryByName(name);
    }
}

