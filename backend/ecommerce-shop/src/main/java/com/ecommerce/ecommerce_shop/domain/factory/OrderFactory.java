package com.ecommerce.ecommerce_shop.domain.factory;

import com.ecommerce.ecommerce_shop.domain.entities.Order;
import com.ecommerce.ecommerce_shop.domain.entities.OrderStatus;
import com.ecommerce.ecommerce_shop.domain.entities.Product;
import com.ecommerce.ecommerce_shop.domain.entities.User;

import java.util.List;

public class OrderFactory {
    public static Order createOrder(User user, List<Product> products) {
        return new Order(user, products, OrderStatus.PENDING);
    }
}
