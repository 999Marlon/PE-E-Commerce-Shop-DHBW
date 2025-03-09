package com.ecommerce.domain.repository;

import com.ecommerce.domain.entities.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Optional<Category> findByName(String name);
    Category save(Category category);
    void deleteById(UUID uuid);
    Optional<Category> findById(UUID id);
    List<Category> findAll();
}
