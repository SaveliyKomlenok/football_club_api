package com.example.footballclubapi.dto.request.clothing;

import java.math.BigDecimal;

public record ClothingRequest(
        String name,
        BigDecimal price,
        String color,
        String material,
        String description,
        String imagePath,
        Long manufacturer,
        Long clothingType
) {
}
