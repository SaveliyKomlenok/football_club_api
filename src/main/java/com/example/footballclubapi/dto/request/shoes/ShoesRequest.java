package com.example.footballclubapi.dto.request.shoes;

import java.math.BigDecimal;

public record ShoesRequest(
        String name,
        BigDecimal price,
        String color,
        String material,
        String soleType,
        String description,
        String imagePath,
        Long manufacturer,
        Long shoesType
) {
}
