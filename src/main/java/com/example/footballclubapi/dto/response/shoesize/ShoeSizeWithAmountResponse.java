package com.example.footballclubapi.dto.response.shoesize;

import lombok.Builder;

@Builder
public record ShoeSizeWithAmountResponse(
        Long id,
        String size,
        Integer amount
) {
}
