package com.example.footballclubapi.dto.response.clothsize;

import lombok.Builder;

@Builder
public record ClothSizeWithAmountResponse(
        Long id,
        String size,
        Integer amount
) {
}
