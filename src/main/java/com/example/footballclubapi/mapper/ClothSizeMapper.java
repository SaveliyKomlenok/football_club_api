package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.clothsize.ClothSizeRequest;
import com.example.footballclubapi.dto.response.clothsize.ClothSizeListResponse;
import com.example.footballclubapi.dto.response.clothsize.ClothSizeResponse;
import com.example.footballclubapi.entity.ClothSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClothSizeMapper {
    public ClothSize toEntity(ClothSizeRequest request) {
        return ClothSize.builder()
                .size(request.size())
                .build();
    }

    public ClothSize toEntity(Long id, ClothSizeRequest request) {
        return ClothSize.builder()
                .id(id)
                .size(request.size())
                .build();
    }

    public ClothSizeResponse toResponse(ClothSize clothSize) {
        return ClothSizeResponse.builder()
                .id(clothSize.getId())
                .size(clothSize.getSize())
                .build();
    }

    public ClothSizeListResponse toListResponse(List<ClothSize> sizeList) {
        List<ClothSizeResponse> responses = sizeList.stream()
                .map(this::toResponse)
                .toList();

        return ClothSizeListResponse.builder()
                .items(responses)
                .build();
    }
}
