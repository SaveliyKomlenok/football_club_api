package com.example.footballclubapi.dto.response.clothing;

import com.example.footballclubapi.dto.response.clothingtype.ClothingTypeResponse;
import com.example.footballclubapi.dto.response.manufacturer.ManufacturerResponse;
import com.example.footballclubapi.entity.ClothSize;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ClothingResponse(
        Long id,
        String name,
        BigDecimal price,
        String color,
        String material,
        String description,
        ManufacturerResponse manufacturer,
        ClothingTypeResponse clothingType
) {
}
