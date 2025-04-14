package com.example.footballclubapi.dto.response.clothingtype;

import lombok.Builder;

import java.util.List;

@Builder
public record ClothingTypeListResponse(
        List<ClothingTypeResponse> items
) {
}
