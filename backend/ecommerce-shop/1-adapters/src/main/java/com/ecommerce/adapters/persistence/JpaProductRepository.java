package com.ecommerce.adapters.persistence;

import com.ecommerce.domain.entities.Category;
import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, UUID>, ProductRepository {
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByCategory(Category category);
}
