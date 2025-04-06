package com.ecommerce.domain.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ecommerce.domain.entities.Cart;
import com.ecommerce.domain.entities.Product;

@Service
public class CartQuantityService {

    public void updateQuantity(Cart cart, Product product, int quantity) {
        List<Product> updatedProducts = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            updatedProducts.add(product);
        }

        cart.getProducts().removeIf(p -> p.getId().equals(product.getId()));
        cart.getProducts().addAll(updatedProducts);
    }
    
}
