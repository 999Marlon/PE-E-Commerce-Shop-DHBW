package com.ecommerce.domain.service;

import com.ecommerce.domain.dto.ProductDTO;
import com.ecommerce.domain.entities.Category;
import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.exceptions.CategoryNotFoundException;
import com.ecommerce.domain.exceptions.ProductNotFoundException;
import com.ecommerce.domain.mappers.ProductMapper;
import com.ecommerce.domain.repository.CategoryRepository;
import com.ecommerce.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById_Found_ShouldReturnProductDTO() {

        UUID productId = UUID.randomUUID();
        
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("Test Product");

        Category mockCategory = new Category("TestCategory");

        mockProduct.setCategory(mockCategory);

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        ProductDTO result = productService.getProductById(productId);

        assertNotNull(result, "ProductDTO sollte nicht null sein.");
        assertEquals(productId, result.getId(), "Die ID des DTO sollte mit der des Mock-Products übereinstimmen.");
        assertEquals("Test Product", result.getName(), "Der Name des DTO sollte 'Test Product' sein.");
    }


    @Test
    void testGetProductById_NotFound_ShouldThrowProductNotFoundException() {

        UUID invalidId = UUID.randomUUID();
        when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> productService.getProductById(invalidId),
                "Sollte eine ProductNotFoundException werfen, wenn kein Produkt gefunden wird.");
    }

    @Test
    void testAddProduct_WithExistingCategory_ShouldUseExistingCategory() {

        String categoryName = "ExistingCategory";
        Product product = new Product();
        product.setName("Product A");
        product.setCategory(new Category(categoryName));

        Category existingCategory = new Category(categoryName);
        when(categoryRepository.findByName(categoryName))
                .thenReturn(Optional.of(existingCategory));

        when(productRepository.save(product)).thenReturn(product);

        ProductDTO result = productService.addProduct(product);

        assertNotNull(result, "Das zurückgegebene ProductDTO sollte nicht null sein.");
        assertEquals("Product A", result.getName(), "Produkt-Name sollte 'Product A' sein.");

        verify(categoryRepository, never()).save(any(Category.class));
        assertEquals(existingCategory, product.getCategory());
    }

    @Test
    void testAddProduct_WithNewCategory_ShouldSaveCategoryAndProduct() {

        String newCategoryName = "NewCategory";
        Product product = new Product();
        product.setName("Product B");
        product.setCategory(new Category(newCategoryName));

        when(categoryRepository.findByName(newCategoryName))
                .thenReturn(Optional.empty());

        Category savedCategory = new Category(newCategoryName);
        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        when(productRepository.save(product)).thenReturn(product);

        ProductDTO result = productService.addProduct(product);

        assertNotNull(result, "Das zurückgegebene ProductDTO sollte nicht null sein.");
        assertEquals("Product B", result.getName(), "Name sollte 'Product B' sein.");

        verify(categoryRepository).save(any(Category.class));
        verify(productRepository).save(product);
    }

    @Test
    void testAddProduct_WithoutCategory_ShouldThrowCategoryNotFoundException() {
        Product product = new Product();
        product.setName("Product C");

        assertThrows(CategoryNotFoundException.class,
                () -> productService.addProduct(product),
                "Sollte eine RuntimeException werfen, wenn keine Kategorie vorhanden ist.");
        
        verify(categoryRepository, never()).findByName(anyString());
        verify(productRepository, never()).save(any(Product.class));
    }
}
