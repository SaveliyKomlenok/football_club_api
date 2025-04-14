package com.example.footballclubapi.dto.response.clothingwarehouse;

import com.example.footballclubapi.dto.response.clothing.ClothingResponse;
import com.example.footballclubapi.dto.response.clothsize.ClothSizeResponse;
import lombok.Builder;

@Builder
public record ClothingWarehouseResponse(
        ClothingResponse clothing,
        ClothSizeResponse size,
        Integer amount
) {
}
