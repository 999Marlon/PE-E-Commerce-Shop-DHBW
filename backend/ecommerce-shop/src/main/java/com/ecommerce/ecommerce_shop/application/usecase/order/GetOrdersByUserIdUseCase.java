package com.ecommerce.ecommerce_shop.application.usecase.order;

import com.ecommerce.ecommerce_shop.domain.repository.OrderRepository;
import com.ecommerce.ecommerce_shop.interfaces.dto.OrderDTO;
import com.ecommerce.ecommerce_shop.interfaces.mappers.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GetOrdersByUserIdUseCase {

    private final OrderRepository orderRepository;

    public GetOrdersByUserIdUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> execute(UUID userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
