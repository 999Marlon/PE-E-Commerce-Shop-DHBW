package com.ecommerce.ecommerce_shop.infrastructure.persistence;

import com.ecommerce.ecommerce_shop.domain.entities.Product;
import com.ecommerce.ecommerce_shop.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, UUID>, ProductRepository {
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByCategory(UUID categoryId);
}
