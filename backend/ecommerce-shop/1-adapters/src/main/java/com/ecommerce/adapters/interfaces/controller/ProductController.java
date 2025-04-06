package com.ecommerce.adapters.interfaces.controller;

import com.ecommerce.application.usecase.product.AddProductUseCase;
import com.ecommerce.application.usecase.product.DeleteProductUseCase;
import com.ecommerce.application.usecase.product.FilterProductsUseCase;
import com.ecommerce.application.usecase.product.GetAllProductsUseCase;
import com.ecommerce.application.usecase.product.GetProductByIdUseCase;
import com.ecommerce.application.usecase.product.GetProductsByCategoryUseCase;
import com.ecommerce.application.usecase.product.SearchProductsUseCase;
import com.ecommerce.application.usecase.product.UpdateProductUseCase;
import com.ecommerce.domain.dto.ProductDTO;
import com.ecommerce.domain.entities.Product;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final GetAllProductsUseCase getAllProductsUseCase;
    private final AddProductUseCase addProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final SearchProductsUseCase searchProductsUseCase;
    private final FilterProductsUseCase filterProductsUseCase;
    private final GetProductsByCategoryUseCase getProductsByCategoryUseCase;

    public ProductController(
        GetAllProductsUseCase getAllProductsUseCase,
        AddProductUseCase addProductUseCase,
        GetProductByIdUseCase getProductByIdUseCase,
        UpdateProductUseCase updateProductUseCase,
        DeleteProductUseCase deleteProductUseCase,
        SearchProductsUseCase searchProductsUseCase,
        FilterProductsUseCase filterProductsUseCase,
        GetProductsByCategoryUseCase getProductsByCategoryUseCase) {
            
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.addProductUseCase = addProductUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.searchProductsUseCase = searchProductsUseCase;
        this.filterProductsUseCase = filterProductsUseCase;
        this.getProductsByCategoryUseCase = getProductsByCategoryUseCase;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return getAllProductsUseCase.execute();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable UUID id) {
        return getProductByIdUseCase.execute(id);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody Product product) {
        return addProductUseCase.execute(product);
    }

    
    @PostMapping("/add")
    public ProductDTO addProduct(@RequestBody Product product) {
        return addProductUseCase.execute(product);
    }

    @PutMapping("/update/{productId}")
    public ProductDTO updateProduct(@PathVariable UUID productId, @RequestBody Product updatedProduct) {
        return updateProductUseCase.execute(productId, updatedProduct);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable UUID productId) {
        deleteProductUseCase.execute(productId);
    }

    @GetMapping("/search")
    public List<ProductDTO> searchProducts(@RequestParam String query) {
        return searchProductsUseCase.execute(query);
    }

    @GetMapping("/filter")
    public List<ProductDTO> filterProducts(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return filterProductsUseCase.execute(minPrice, maxPrice);
    }

    @GetMapping("/category/{categoryName}")
    public List<ProductDTO> getProductsByCategory(@PathVariable String categoryName) {
        return getProductsByCategoryUseCase.execute(categoryName);
    }
}
