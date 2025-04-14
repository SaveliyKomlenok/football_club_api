package com.example.footballclubapi.dto.request.shoeswarehouse;

public record ShoesWarehouseRequest(
        Long shoes,
        Long size,
        Integer amount
) {
}
