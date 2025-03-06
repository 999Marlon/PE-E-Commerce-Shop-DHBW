package com.ecommerce.ecommerce_shop.domain.repository;

import com.ecommerce.ecommerce_shop.domain.entities.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Optional<Category> findByName(String name);
    Category save(Category category);
    void delete(UUID id);
    Optional<Category> findById(UUID id);
    List<Category> findAll();
    List<Category> findByParentCategory(UUID parentCategoryId);
}
