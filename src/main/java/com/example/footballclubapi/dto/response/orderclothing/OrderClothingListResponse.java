package com.example.footballclubapi.dto.response.orderclothing;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderClothingListResponse(
        List<OrderClothingResponse> items
) {
}
