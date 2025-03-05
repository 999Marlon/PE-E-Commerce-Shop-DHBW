package com.ecommerce.ecommerce_shop.domain.service;

import com.ecommerce.ecommerce_shop.domain.model.Product;
import com.ecommerce.ecommerce_shop.domain.repository.CategoryRepository;
import com.ecommerce.ecommerce_shop.domain.repository.ProductRepository;
import com.ecommerce.ecommerce_shop.domain.model.Category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    
    private final ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        if (product.getCategory() == null || product.getCategory().getName() == null) {
            throw new RuntimeException("Category must not be null");
        }
    
        Category category = categoryRepository.findByName(product.getCategory().getName())
                .orElseGet(() -> categoryRepository.save(new Category(product.getCategory().getName())));
    
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nicht gefunden mit ID: " + id));
    }
    

    public Product updateProduct(UUID productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> searchProducts(String query) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    public List<Product> filterProducts(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return productRepository.findByCategory(category);
    }
}
