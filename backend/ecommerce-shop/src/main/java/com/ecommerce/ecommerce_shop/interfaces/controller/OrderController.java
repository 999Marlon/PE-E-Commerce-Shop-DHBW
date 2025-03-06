package com.ecommerce.ecommerce_shop.interfaces.controller;

import com.ecommerce.ecommerce_shop.application.usecase.order.GetOrdersByUserIdUseCase;
import com.ecommerce.ecommerce_shop.application.usecase.order.PlaceOrderUseCase;
import com.ecommerce.ecommerce_shop.application.usecase.order.UpdateOrderStatusUseCase;
import com.ecommerce.ecommerce_shop.domain.entities.OrderStatus;
import com.ecommerce.ecommerce_shop.interfaces.dto.OrderDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    private final PlaceOrderUseCase placeOrderUseCase;
    private final GetOrdersByUserIdUseCase getOrdersByUserIdUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    public OrderController(PlaceOrderUseCase placeOrderUseCase,GetOrdersByUserIdUseCase getOrdersByUserIdUseCase, UpdateOrderStatusUseCase updateOrderStatusUseCase) {
        this.placeOrderUseCase = placeOrderUseCase;
        this.getOrdersByUserIdUseCase = getOrdersByUserIdUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
    }

    @PostMapping("/place/{userId}")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable UUID userId) {
        OrderDTO orderDTO = placeOrderUseCase.execute(userId);
        return ResponseEntity.ok(orderDTO); 
    }

    @GetMapping("/{userId}")
    public List<OrderDTO> getUserOrders(@PathVariable UUID userId) {
        return getOrdersByUserIdUseCase.execute(userId);
    }

    @PutMapping("/update-status/{orderId}")
    public OrderDTO updateOrderStatus(@PathVariable UUID orderId, @RequestParam OrderStatus status) {
        return updateOrderStatusUseCase.execute(orderId, status);
    }

}
