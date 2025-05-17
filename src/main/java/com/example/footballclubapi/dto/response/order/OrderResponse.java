package com.example.footballclubapi.dto.response.order;

import com.example.footballclubapi.dto.response.orderclothing.OrderClothingResponse;
import com.example.footballclubapi.dto.response.ordershoes.OrderShoesResponse;
import com.example.footballclubapi.dto.response.user.UserResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponse(
        Long id,
        LocalDateTime dateOfCreation,
        String address,
        UserResponse user,
        BigDecimal totalPrice,
        List<OrderClothingResponse> orderClothingList,
        List<OrderShoesResponse> orderShoesList
) {
}
