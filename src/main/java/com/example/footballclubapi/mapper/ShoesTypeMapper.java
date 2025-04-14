package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.shoestype.ShoesTypeRequest;
import com.example.footballclubapi.dto.response.shoestype.ShoesTypeListResponse;
import com.example.footballclubapi.dto.response.shoestype.ShoesTypeResponse;
import com.example.footballclubapi.entity.ShoesType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShoesTypeMapper {
    public ShoesType toEntity(ShoesTypeRequest request) {
        return ShoesType.builder()
                .name(request.name())
                .build();
    }

    public ShoesType toEntity(Long id, ShoesTypeRequest request) {
        return ShoesType.builder()
                .id(id)
                .name(request.name())
                .build();
    }

    public ShoesTypeResponse toResponse(ShoesType shoesType) {
        return ShoesTypeResponse.builder()
                .id(shoesType.getId())
                .name(shoesType.getName())
                .build();
    }

    public ShoesTypeListResponse toListResponse(List<ShoesType> shoesTypeList) {
        List<ShoesTypeResponse> responses = shoesTypeList.stream()
                .map(this::toResponse)
                .toList();

        return ShoesTypeListResponse.builder()
                .items(responses)
                .build();
    }
}
