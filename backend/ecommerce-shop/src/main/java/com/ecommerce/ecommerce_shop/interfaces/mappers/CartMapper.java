package com.ecommerce.ecommerce_shop.interfaces.mappers;

import java.util.stream.Collectors;

import com.ecommerce.ecommerce_shop.domain.entities.Cart;
import com.ecommerce.ecommerce_shop.interfaces.dto.CartDTO;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setProductIds(cart.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList()));
        return dto;
    }
    
}
