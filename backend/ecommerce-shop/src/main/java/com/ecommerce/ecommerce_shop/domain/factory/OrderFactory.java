package com.ecommerce.ecommerce_shop.domain.factory;

import com.ecommerce.ecommerce_shop.domain.model.Order;
import com.ecommerce.ecommerce_shop.domain.model.OrderStatus;
import com.ecommerce.ecommerce_shop.domain.model.Product;
import com.ecommerce.ecommerce_shop.domain.model.User;

import java.util.List;

public class OrderFactory {
    public static Order createOrder(User user, List<Product> products) {
        return new Order(user, products, OrderStatus.PENDING);
    }
}
