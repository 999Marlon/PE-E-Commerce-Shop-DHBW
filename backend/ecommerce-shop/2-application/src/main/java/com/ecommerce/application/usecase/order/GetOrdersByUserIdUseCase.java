package com.ecommerce.application.usecase.order;

import com.ecommerce.domain.repository.OrderRepository;
import com.ecommerce.domain.dto.OrderDTO;
import com.ecommerce.domain.mappers.OrderMapper;
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
