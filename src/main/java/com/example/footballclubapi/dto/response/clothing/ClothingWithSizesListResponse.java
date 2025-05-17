package com.example.footballclubapi.dto.response.clothing;

import lombok.Builder;

import java.util.List;

@Builder
public record ClothingWithSizesListResponse(
        List<ClothingWithSizesResponse> items
) {
}
