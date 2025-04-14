package com.example.footballclubapi.dto.response.clothsize;

import lombok.Builder;

@Builder
public record ClothSizeResponse(
        Long id,
        String size
) {
}
