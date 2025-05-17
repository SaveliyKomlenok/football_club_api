package com.example.footballclubapi.dto.response.clothing;

import com.example.footballclubapi.dto.response.clothsize.ClothSizeWithAmountResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ClothingWithSizesResponse(
        ClothingResponse clothing,
        List<ClothSizeWithAmountResponse> sizes
) {
}
