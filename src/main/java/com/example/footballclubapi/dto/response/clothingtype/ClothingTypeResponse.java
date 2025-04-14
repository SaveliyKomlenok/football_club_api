package com.example.footballclubapi.dto.response.clothingtype;

import lombok.Builder;

@Builder
public record ClothingTypeResponse(
        Long id,
        String name
) {
}
