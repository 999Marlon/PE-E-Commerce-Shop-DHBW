package com.ecommerce.ecommerce_shop.application.usecase.category;

import com.ecommerce.ecommerce_shop.domain.service.CategoryService;
import com.ecommerce.ecommerce_shop.interfaces.dto.CategoryDTO;
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
