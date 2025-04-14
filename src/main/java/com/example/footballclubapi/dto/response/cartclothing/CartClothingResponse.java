package com.example.footballclubapi.dto.response.cartclothing;

import com.example.footballclubapi.dto.response.clothingwarehouse.ClothingWarehouseResponse;
import lombok.Builder;

@Builder
public record CartClothingResponse(
        Long id,
        Integer amount,
        ClothingWarehouseResponse clothing
) {
}
