package com.ecommerce.ecommerce_shop.infrastructure.persistence;

import com.ecommerce.ecommerce_shop.domain.entities.Category;
import com.ecommerce.ecommerce_shop.domain.repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, UUID>, CategoryRepository {
    Optional<Category> findByName(String name);
}
