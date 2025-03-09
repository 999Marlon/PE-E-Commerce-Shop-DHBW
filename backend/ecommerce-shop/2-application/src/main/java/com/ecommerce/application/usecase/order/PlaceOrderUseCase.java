package com.ecommerce.application.usecase.order;

import com.ecommerce.domain.entities.Order;
import com.ecommerce.domain.entities.OrderStatus;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.repository.CartRepository;
import com.ecommerce.domain.repository.OrderRepository;
import com.ecommerce.domain.repository.UserRepository;
import com.ecommerce.domain.dto.OrderDTO;
import com.ecommerce.domain.mappers.OrderMapper;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

import java.util.UUID;

@Component
public class PlaceOrderUseCase {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public PlaceOrderUseCase(OrderRepository orderRepository, CartRepository cartRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public OrderDTO execute(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty, cannot place order");
        }

        Order order = new Order();
        order.setUser(user);
        order.setProducts(cart.getProducts());
        order.setStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);

        cartRepository.deleteById(cart.getId());

        return OrderMapper.toDTO(savedOrder);
    }
}
