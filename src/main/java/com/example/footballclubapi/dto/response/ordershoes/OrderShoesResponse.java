package com.example.footballclubapi.dto.response.ordershoes;

import com.example.footballclubapi.dto.response.shoeswarehouse.ShoesWarehouseResponse;
import lombok.Builder;

@Builder
public record OrderShoesResponse(
        Long id,
        Integer amount,
        ShoesWarehouseResponse shoesWarehouse
) {
}
