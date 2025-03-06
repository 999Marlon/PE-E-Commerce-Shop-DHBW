package com.ecommerce.ecommerce_shop.application.usecase.category;

import com.ecommerce.ecommerce_shop.domain.service.CategoryService;
import com.ecommerce.ecommerce_shop.interfaces.dto.CategoryDTO;
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

