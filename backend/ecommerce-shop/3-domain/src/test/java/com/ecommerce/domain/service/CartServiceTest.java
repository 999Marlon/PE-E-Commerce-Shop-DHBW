package com.ecommerce.domain.service;

import com.ecommerce.domain.dto.CartDTO;
import com.ecommerce.domain.entities.Cart;
import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.exceptions.CartNotFoundException;
import com.ecommerce.domain.exceptions.ProductNotFoundException;
import com.ecommerce.domain.exceptions.UserNotFoundException;
import com.ecommerce.domain.repository.CartRepository;
import com.ecommerce.domain.repository.ProductRepository;
import com.ecommerce.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddToCart_Success() {

        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        User mockUser = new User();
        mockUser.setId(userId);
        
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.empty());
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        Cart savedCart = new Cart();
        savedCart.setId(UUID.randomUUID());
        savedCart.setUser(mockUser);
        savedCart.setProducts(new ArrayList<>(Collections.singletonList(mockProduct)));

        when(cartRepository.save(any(Cart.class))).thenReturn(savedCart);

        CartDTO result = cartService.addToCart(userId, productId);

        assertNotNull(result, "Das Ergebnis (CartDTO) sollte nicht null sein.");
        assertEquals(savedCart.getId(), result.getId(), "Die Cart-ID sollte der gemockten ID entsprechen.");

        assertFalse(result.getProductIds().isEmpty(), "Die Produktliste sollte mindestens 1 Eintrag haben.");
    }

    @Test
    void testAddToCart_UserNotFound() {

        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> cartService.addToCart(userId, productId),
                "Sollte UserNotFoundException werfen, wenn der Benutzer nicht existiert.");
        
        verify(cartRepository, never()).save(any(Cart.class));
    }

    @Test
    void testAddToCart_ProductNotFound() {

        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        User mockUser = new User();
        mockUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.empty());

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> cartService.addToCart(userId, productId),
                "Sollte eine ProductNotFoundException werfen, wenn das Produkt nicht existiert.");
    }

    @Test
    void testGetCartByUserId_Found() {
        UUID userId = UUID.randomUUID();
        Cart existingCart = new Cart();
        existingCart.setId(UUID.randomUUID());
        existingCart.setUser(new User());
        existingCart.setProducts(new ArrayList<>());

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(existingCart));

        CartDTO result = cartService.getCartByUserId(userId);

        assertNotNull(result, "CartDTO sollte nicht null sein.");
        assertEquals(existingCart.getId(), result.getId(), "ID sollte mit der Cart-ID übereinstimmen.");
    }

    @Test
    void testGetCartByUserId_NotFound() {
        UUID userId = UUID.randomUUID();
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(CartNotFoundException.class,
                () -> cartService.getCartByUserId(userId),
                "Sollte eine CartNotFoundException werfen, wenn kein Cart für den User existiert.");
    }

    @Test
    void testRemoveFromCart_Success() {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        User mockUser = new User();
        mockUser.setId(userId);

        Product product = new Product();
        product.setId(productId);

        Cart existingCart = new Cart();
        existingCart.setId(UUID.randomUUID());
        existingCart.setUser(mockUser);
        existingCart.setProducts(new ArrayList<>(Collections.singletonList(product)));

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);

        CartDTO result = cartService.removeFromCart(userId, productId);

        assertNotNull(result);
    }


    @Test
    void testUpdateProductQuantity_ShouldSetMultipleEntries() {

        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        int quantity = 3;

        User mockUser = new User();
        mockUser.setId(userId);

        Cart existingCart = new Cart();
        existingCart.setId(UUID.randomUUID());
        existingCart.setUser(mockUser);
        existingCart.setProducts(new ArrayList<>());

        Product mockProduct = new Product();
        mockProduct.setId(productId);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(existingCart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);

        CartDTO result = cartService.updateProductQuantity(userId, productId, quantity);

        assertNotNull(result, "CartDTO sollte nicht null sein.");
        assertEquals(3, result.getProductIds().size(), "Es sollten 3 Einträge für das Produkt im Warenkorb sein.");
    }

    @Test
    void testUpdateProductQuantity_ShouldRemoveWhenZero() {

        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        int quantity = 0; 

        User mockUser = new User();
        mockUser.setId(userId);

        Cart existingCart = new Cart();
        existingCart.setId(UUID.randomUUID());
        existingCart.setUser(mockUser); 
        existingCart.setProducts(new ArrayList<>());

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);

        CartDTO result = cartService.updateProductQuantity(userId, productId, quantity);

        assertNotNull(result);
        assertTrue(result.getProductIds().isEmpty(), "Nach remove sollte die Produktliste leer sein.");
    }

}
