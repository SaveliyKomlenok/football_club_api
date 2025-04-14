package com.example.footballclubapi.dto.response.clothingwarehouse;

import lombok.Builder;

import java.util.List;

@Builder
public record ClothingWarehouseListResponse(
        List<ClothingWarehouseResponse> items
) {
}
