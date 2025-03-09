package com.ecommerce.domain.mappers;

import java.util.stream.Collectors;

import com.ecommerce.domain.dto.CartDTO;
import com.ecommerce.domain.entities.Cart;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setProductIds(cart.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList()));
        return dto;
    }
    
}
