package com.ecommerce.application.usecase.order;

import com.ecommerce.domain.entities.Order;
import com.ecommerce.domain.entities.OrderStatus;
import com.ecommerce.domain.repository.OrderRepository;
import com.ecommerce.domain.dto.OrderDTO;
import com.ecommerce.domain.mappers.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateOrderStatusUseCase {

    private final OrderRepository orderRepository;

    public UpdateOrderStatusUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO execute(UUID orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    
        order.setStatus(status);
        return OrderMapper.toDTO(orderRepository.save(order));
    }
}
