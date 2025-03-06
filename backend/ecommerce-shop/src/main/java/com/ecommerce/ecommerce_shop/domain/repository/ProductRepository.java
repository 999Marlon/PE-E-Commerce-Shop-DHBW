package com.ecommerce.ecommerce_shop.domain.repository;

import com.ecommerce.ecommerce_shop.domain.entities.Category;
import com.ecommerce.ecommerce_shop.domain.entities.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository{
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByCategory(Category category);
    Optional<Product> findById(UUID id);
    List<Product> findAll();
    Product save(Product product);
    void delete(Product product);
}
