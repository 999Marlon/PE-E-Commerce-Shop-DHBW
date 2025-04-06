package com.ecommerce.application.usecase.order;

import com.ecommerce.domain.entities.OrderStatus;
import com.ecommerce.domain.service.OrderService;
import com.ecommerce.domain.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateOrderStatusUseCase {

    private final OrderService orderService;

    public UpdateOrderStatusUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderDTO execute(UUID orderId, OrderStatus status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}

