package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.clothingtype.ClothingTypeRequest;
import com.example.footballclubapi.dto.response.clothingtype.ClothingTypeListResponse;
import com.example.footballclubapi.dto.response.clothingtype.ClothingTypeResponse;
import com.example.footballclubapi.entity.ClothingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClothingTypeMapper {
    public ClothingType toEntity(ClothingTypeRequest request) {
        return ClothingType.builder()
                .name(request.name())
                .build();
    }

    public ClothingType toEntity(Long id, ClothingTypeRequest request) {
        return ClothingType.builder()
                .id(id)
                .name(request.name())
                .build();
    }

    public ClothingTypeResponse toResponse(ClothingType clothingType) {
        return ClothingTypeResponse.builder()
                .id(clothingType.getId())
                .name(clothingType.getName())
                .build();
    }

    public ClothingTypeListResponse toListResponse(List<ClothingType> clothingTypeList) {
        List<ClothingTypeResponse> responses = clothingTypeList.stream()
                .map(this::toResponse)
                .toList();
        return ClothingTypeListResponse.builder()
                .items(responses)
                .build();
    }
}

