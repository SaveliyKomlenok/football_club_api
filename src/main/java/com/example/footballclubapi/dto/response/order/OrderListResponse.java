package com.example.footballclubapi.dto.response.order;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderListResponse(
        List<OrderResponse> items
) {
}
