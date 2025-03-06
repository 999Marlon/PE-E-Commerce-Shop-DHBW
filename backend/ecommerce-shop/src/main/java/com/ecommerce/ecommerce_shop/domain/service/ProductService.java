package com.ecommerce.ecommerce_shop.domain.service;

import com.ecommerce.ecommerce_shop.domain.repository.CategoryRepository;
import com.ecommerce.ecommerce_shop.domain.repository.ProductRepository;
import com.ecommerce.ecommerce_shop.interfaces.dto.ProductDTO;
import com.ecommerce.ecommerce_shop.interfaces.mappers.ProductMapper;
import com.ecommerce.ecommerce_shop.domain.entities.Category;
import com.ecommerce.ecommerce_shop.domain.entities.Product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    
    private final ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO addProduct(Product product) {
        if (product.getCategory() == null || product.getCategory().getName() == null) {
            throw new RuntimeException("Category must not be null");
        }
    
        Category category = categoryRepository.findByName(product.getCategory().getName())
                .orElseGet(() -> categoryRepository.save(new Category(product.getCategory().getName())));
    
        product.setCategory(category);
        return ProductMapper.toDTO(productRepository.save(product));
    }

    public ProductDTO getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return ProductMapper.toDTO(product);
    }
    

    public ProductDTO updateProduct(UUID productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        return ProductMapper.toDTO(productRepository.save(existingProduct));
    }

    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    public List<ProductDTO> searchProducts(String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> filterProducts(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
