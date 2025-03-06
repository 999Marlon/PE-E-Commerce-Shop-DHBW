package com.ecommerce.ecommerce_shop.application.usecase.order;

import com.ecommerce.ecommerce_shop.domain.entities.Order;
import com.ecommerce.ecommerce_shop.domain.entities.OrderStatus;
import com.ecommerce.ecommerce_shop.domain.repository.OrderRepository;
import com.ecommerce.ecommerce_shop.interfaces.dto.OrderDTO;
import com.ecommerce.ecommerce_shop.interfaces.mappers.OrderMapper;
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
