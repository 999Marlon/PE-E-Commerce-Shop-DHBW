package com.ecommerce.application.usecase.order;

import com.ecommerce.domain.service.OrderService;
import com.ecommerce.domain.dto.OrderDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
public class GetOrdersByUserIdUseCase {

    private final OrderService orderService;

    public GetOrdersByUserIdUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<OrderDTO> execute(UUID userId) {
        return orderService.getOrdersByUserId(userId);
    }
}
