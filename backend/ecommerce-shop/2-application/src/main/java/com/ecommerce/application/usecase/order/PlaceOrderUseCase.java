package com.ecommerce.application.usecase.order;

import com.ecommerce.domain.service.OrderService;
import com.ecommerce.domain.dto.OrderDTO;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class PlaceOrderUseCase {

    private final OrderService orderService;

    public PlaceOrderUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderDTO execute(UUID userId) {
        return orderService.placeOrder(userId);
    }
}
