package com.ecommerce.ecommerce_shop.application.usecase.category;

import com.ecommerce.ecommerce_shop.domain.entities.Category;
import com.ecommerce.ecommerce_shop.domain.service.CategoryService;
import com.ecommerce.ecommerce_shop.interfaces.dto.CategoryDTO;
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

