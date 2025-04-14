package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.manufacturer.ManufacturerRequest;
import com.example.footballclubapi.dto.response.manufacturer.ManufacturerListResponse;
import com.example.footballclubapi.dto.response.manufacturer.ManufacturerResponse;
import com.example.footballclubapi.entity.Manufacturer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ManufacturerMapper {
    public Manufacturer toEntity(ManufacturerRequest request) {
        return Manufacturer.builder()
                .name(request.name())
                .build();
    }

    public Manufacturer toEntity(Long id, ManufacturerRequest request) {
        return Manufacturer.builder()
                .id(id)
                .name(request.name())
                .build();
    }

    public ManufacturerResponse toResponse(Manufacturer manufacturer) {
        return ManufacturerResponse.builder()
                .id(manufacturer.getId())
                .name(manufacturer.getName())
                .build();
    }

    public ManufacturerListResponse toListResponse(List<Manufacturer> manufacturerList) {
        List<ManufacturerResponse> manufacturerResponseList = manufacturerList.stream()
                .map(this::toResponse)
                .toList();
        return ManufacturerListResponse.builder()
                .items(manufacturerResponseList)
                .build();
    }
}
