package com.ecommerce.ecommerce_shop.interfaces.controller;

import com.ecommerce.ecommerce_shop.application.usecase.category.AddCategoryUseCase;
import com.ecommerce.ecommerce_shop.application.usecase.category.GetAllCategoriesUseCase;
import com.ecommerce.ecommerce_shop.application.usecase.category.GetCategoryByNameUseCase;
import com.ecommerce.ecommerce_shop.domain.entities.Category;
import com.ecommerce.ecommerce_shop.interfaces.dto.CategoryDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final AddCategoryUseCase addCategoryUseCase;
    private final GetCategoryByNameUseCase getCategoryByNameUseCase;

    public CategoryController(GetAllCategoriesUseCase getAllCategoriesUseCase,AddCategoryUseCase addCategoryUseCase,GetCategoryByNameUseCase getCategoryByNameUseCase) {
        this.getAllCategoriesUseCase = getAllCategoriesUseCase;
        this.addCategoryUseCase = addCategoryUseCase;
        this.getCategoryByNameUseCase = getCategoryByNameUseCase;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return getAllCategoriesUseCase.execute();
    }

    @PostMapping("/add")
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getName());
        return addCategoryUseCase.execute(category);
    }

    @GetMapping("/{name}")
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return getCategoryByNameUseCase.execute(name);
    }
}
