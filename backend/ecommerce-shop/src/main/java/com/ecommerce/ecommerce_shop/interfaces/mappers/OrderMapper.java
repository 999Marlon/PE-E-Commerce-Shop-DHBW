package com.ecommerce.ecommerce_shop.interfaces.mappers;

import java.util.stream.Collectors;

import com.ecommerce.ecommerce_shop.domain.entities.Order;
import com.ecommerce.ecommerce_shop.interfaces.dto.OrderDTO;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setProductIds(order.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList()));
        dto.setStatus(order.getStatus().name());
        return dto;
    }
    
}
