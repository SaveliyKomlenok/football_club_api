package com.example.footballclubapi.dto.response.orderclothing;

import com.example.footballclubapi.dto.response.clothingwarehouse.ClothingWarehouseResponse;
import com.example.footballclubapi.dto.response.order.OrderResponse;
import lombok.Builder;

@Builder
public record OrderClothingResponse(
        Long id,
        Integer amount,
        ClothingWarehouseResponse clothingWarehouse,
        OrderResponse order
) {
}
