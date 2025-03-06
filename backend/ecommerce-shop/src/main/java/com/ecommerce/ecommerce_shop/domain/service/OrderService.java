package com.ecommerce.ecommerce_shop.domain.service;

import com.ecommerce.ecommerce_shop.domain.entities.Order;
import com.ecommerce.ecommerce_shop.domain.entities.OrderStatus;
import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.domain.repository.CartRepository;
import com.ecommerce.ecommerce_shop.domain.repository.OrderRepository;
import com.ecommerce.ecommerce_shop.domain.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public Order placeOrder(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty, cannot place order");
        }

        Order order = new Order();
        order.setUser(user);
        order.setProducts(cart.getProducts());
        order.setStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);

        cartRepository.deleteById(cart.getId());

        return savedOrder;
    }

    
    public List<Order> getOrdersByUserId(UUID userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(UUID orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    
        order.setStatus(status);
        return orderRepository.save(order);
    }

}
