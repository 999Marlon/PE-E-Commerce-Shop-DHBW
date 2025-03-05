package com.ecommerce.ecommerce_shop.domain.service;

import com.ecommerce.ecommerce_shop.domain.model.Order;
import com.ecommerce.ecommerce_shop.domain.model.OrderStatus;
import com.ecommerce.ecommerce_shop.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId(UUID.randomUUID());
        order.setStatus(OrderStatus.PENDING);
    }

    @Test
    void testUpdateOrderStatus() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.updateOrderStatus(order.getId(), OrderStatus.SHIPPED);
        assertEquals(OrderStatus.SHIPPED, updatedOrder.getStatus());

        verify(orderRepository, times(1)).save(order);
    }
}
