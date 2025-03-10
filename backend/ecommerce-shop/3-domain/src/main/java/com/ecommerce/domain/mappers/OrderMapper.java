package com.ecommerce.domain.mappers;

import java.util.stream.Collectors;

import com.ecommerce.domain.dto.OrderDTO;
import com.ecommerce.domain.entities.Order;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setAddress(order.getShippingAddress()); 
        dto.setProductIds(order.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList()));
        dto.setStatus(order.getStatus().name());
        return dto;
    }
    
}
