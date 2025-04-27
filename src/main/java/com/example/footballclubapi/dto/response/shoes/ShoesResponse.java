package com.example.footballclubapi.dto.response.shoes;

import com.example.footballclubapi.dto.response.manufacturer.ManufacturerResponse;
import com.example.footballclubapi.dto.response.shoestype.ShoesTypeResponse;
import com.example.footballclubapi.entity.ClothSize;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ShoesResponse(
        Long id,
        String name,
        BigDecimal price,
        String color,
        String material,
        String soleType,
        String description,
        ManufacturerResponse manufacturer,
        ShoesTypeResponse shoesType
) {
}
