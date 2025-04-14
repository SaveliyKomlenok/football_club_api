package com.example.footballclubapi.dto.response.shoestype;

import lombok.Builder;

import java.util.List;

@Builder
public record ShoesTypeListResponse(
        List<ShoesTypeResponse> items
) {
}
