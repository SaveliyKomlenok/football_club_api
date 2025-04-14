package com.example.footballclubapi.dto.response.shoesize;

import lombok.Builder;

@Builder
public record ShoeSizeResponse(
        Long id,
        String size
) {
}
