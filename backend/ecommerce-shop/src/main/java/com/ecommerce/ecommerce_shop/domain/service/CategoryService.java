package com.ecommerce.ecommerce_shop.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce_shop.domain.entities.Category;
import com.ecommerce.ecommerce_shop.domain.repository.CategoryRepository;
import com.ecommerce.ecommerce_shop.interfaces.dto.CategoryDTO;
import com.ecommerce.ecommerce_shop.interfaces.mappers.CategoryMapper;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDTO) 
                .collect(Collectors.toList());
    }

    public CategoryDTO addCategory(Category category) {
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    public CategoryDTO getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .map(CategoryMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}

