package com.example.footballclubapi.dto.response.shoesize;

import lombok.Builder;

import java.util.List;

@Builder
public record ShoeSizeListResponse(
        List<ShoeSizeResponse> items
) {
}
