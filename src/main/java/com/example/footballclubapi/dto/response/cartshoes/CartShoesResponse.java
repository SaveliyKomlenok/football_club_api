package com.example.footballclubapi.dto.response.cartshoes;

import com.example.footballclubapi.dto.response.shoeswarehouse.ShoesWarehouseResponse;
import lombok.Builder;

@Builder
public record CartShoesResponse(
        Long id,
        Integer amount,
        ShoesWarehouseResponse shoes
) {
}
