package com.ecommerce.domain.factory;

import com.ecommerce.domain.entities.Order;
import com.ecommerce.domain.entities.OrderStatus;
import com.ecommerce.domain.entities.Product;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.valueobjects.Address;

import java.util.List;

public class OrderFactory {
    public static Order createOrder(User user,Address address, List<Product> products) {
        return new Order(user,address, products, OrderStatus.PENDING);
    }
}
