package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.shoesize.ShoeSizeRequest;
import com.example.footballclubapi.dto.response.shoesize.ShoeSizeListResponse;
import com.example.footballclubapi.dto.response.shoesize.ShoeSizeResponse;
import com.example.footballclubapi.entity.ShoeSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShoeSizeMapper {
    public ShoeSize toEntity(ShoeSizeRequest request) {
        return ShoeSize.builder()
                .size(request.size())
                .build();
    }

    public ShoeSize toEntity(Long id, ShoeSizeRequest request) {
        return ShoeSize.builder()
                .id(id)
                .size(request.size())
                .build();
    }

    public ShoeSizeResponse toResponse(ShoeSize shoeSize) {
        return ShoeSizeResponse.builder()
                .id(shoeSize.getId())
                .size(shoeSize.getSize())
                .build();
    }

    public ShoeSizeListResponse toListResponse(List<ShoeSize> shoeSizeList) {
        List<ShoeSizeResponse> responses = shoeSizeList.stream()
                .map(this::toResponse)
                .toList();
        return ShoeSizeListResponse.builder()
                .items(responses)
                .build();
    }
}
