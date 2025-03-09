package com.ecommerce.application.usecase.category;

import com.ecommerce.domain.entities.Category;
import com.ecommerce.domain.service.CategoryService;
import com.ecommerce.domain.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class AddCategoryUseCase {

    private final CategoryService categoryService;

    public AddCategoryUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryDTO execute(Category category) {
        return categoryService.addCategory(category);
    }
}

