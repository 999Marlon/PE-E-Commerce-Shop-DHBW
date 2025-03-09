package com.ecommerce.application.usecase.category;

import com.ecommerce.domain.service.CategoryService;
import com.ecommerce.domain.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllCategoriesUseCase {

    private final CategoryService categoryService;

    public GetAllCategoriesUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<CategoryDTO> execute() {
        return categoryService.getAllCategories();
    }
}
