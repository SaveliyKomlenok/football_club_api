package com.example.footballclubapi.dto.request.ordershoes;

public record OrderShoesRequest(
        Integer amount,
        Long shoes,
        Long order
) {
}
