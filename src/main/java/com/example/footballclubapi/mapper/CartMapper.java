package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.response.cart.CartResponse;
import com.example.footballclubapi.entity.CartClothing;
import com.example.footballclubapi.entity.CartShoes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartMapper {
    private final CartClothingMapper cartClothingMapper;
    private final CartShoesMapper cartShoesMapper;

    public CartResponse toResponse(List<CartClothing> cartClothingList, List<CartShoes> cartShoesList, BigDecimal totalPrice) {
        return CartResponse.builder()
                .cartClothingList(cartClothingList.stream()
                        .map(cartClothingMapper::toResponse)
                        .toList())
                .cartShoesList(cartShoesList.stream()
                        .map(cartShoesMapper::toResponse)
                        .toList())
                .totalPrice(totalPrice)
                .build();
    }
}