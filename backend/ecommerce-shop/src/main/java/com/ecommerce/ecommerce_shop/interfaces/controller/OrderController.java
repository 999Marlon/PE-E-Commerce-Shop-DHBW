package com.ecommerce.ecommerce_shop.interfaces.controller;

import com.ecommerce.ecommerce_shop.domain.entities.Order;
import com.ecommerce.ecommerce_shop.domain.entities.OrderStatus;
import com.ecommerce.ecommerce_shop.domain.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable UUID userId) {
        Order order = orderService.placeOrder(userId);
        return ResponseEntity.ok(order); 
    }

    @GetMapping("/{userId}")
    public List<Order> getUserOrders(@PathVariable UUID userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @PutMapping("/update-status/{orderId}")
    public Order updateOrderStatus(@PathVariable UUID orderId, @RequestParam OrderStatus status) {
        return orderService.updateOrderStatus(orderId, status);
    }

}
