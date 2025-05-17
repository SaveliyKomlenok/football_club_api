package com.example.footballclubapi.dto.response.shoes;

import com.example.footballclubapi.dto.response.shoesize.ShoeSizeWithAmountResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ShoesWithSizesResponse(
        ShoesResponse shoes,
        List<ShoeSizeWithAmountResponse> sizes
) {
}
