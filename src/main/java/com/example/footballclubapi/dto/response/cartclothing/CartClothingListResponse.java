package com.example.footballclubapi.dto.response.cartclothing;

import lombok.Builder;

import java.util.List;

@Builder
public record CartClothingListResponse(
        List<CartClothingResponse> items
) {
}
