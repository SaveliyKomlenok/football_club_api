package com.example.footballclubapi.dto.response.shoeswarehouse;

import com.example.footballclubapi.dto.response.shoes.ShoesResponse;
import com.example.footballclubapi.dto.response.shoesize.ShoeSizeResponse;
import lombok.Builder;

@Builder
public record ShoesWarehouseResponse(
        ShoesResponse shoes,
        ShoeSizeResponse size,
        Integer amount
) {
}
