package com.ecommerce.ecommerce_shop.domain.repository;

import com.ecommerce.ecommerce_shop.domain.model.Category;
import com.ecommerce.ecommerce_shop.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByCategory(Category category);
}
