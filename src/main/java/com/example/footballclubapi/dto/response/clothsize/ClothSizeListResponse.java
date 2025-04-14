package com.example.footballclubapi.dto.response.clothsize;

import lombok.Builder;

import java.util.List;

@Builder
public record ClothSizeListResponse(
        List<ClothSizeResponse> items
) {
}
