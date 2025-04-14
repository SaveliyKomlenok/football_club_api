package com.example.footballclubapi.dto.response.cart;

import com.example.footballclubapi.dto.response.cartclothing.CartClothingResponse;
import com.example.footballclubapi.dto.response.cartshoes.CartShoesResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CartResponse(
        List<CartClothingResponse> cartClothingList,
        List<CartShoesResponse> cartShoesList,
        BigDecimal totalPrice
) {
}
