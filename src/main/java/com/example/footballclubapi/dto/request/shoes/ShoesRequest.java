package com.example.footballclubapi.dto.request.shoes;

import com.example.footballclubapi.entity.ClothSize;

import java.math.BigDecimal;
import java.util.List;

public record ShoesRequest(
        String name,
        BigDecimal price,
        Integer amount,
        String color,
        String material,
        String soleType,
        List<ClothSize> sizes,
        String description,
        String imagePath,
        Long manufacturer,
        Long shoesType
) {
}
