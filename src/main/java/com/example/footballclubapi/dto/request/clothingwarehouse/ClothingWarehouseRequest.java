package com.example.footballclubapi.dto.request.clothingwarehouse;

public record ClothingWarehouseRequest(
        Long clothing,
        Long size,
        Integer amount
) {
}
