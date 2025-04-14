package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.clothingwarehouse.ClothingWarehouseRequest;
import com.example.footballclubapi.dto.response.clothingwarehouse.ClothingWarehouseListResponse;
import com.example.footballclubapi.dto.response.clothsize.ClothSizeResponse;
import com.example.footballclubapi.dto.response.clothing.ClothingResponse;
import com.example.footballclubapi.dto.response.clothingwarehouse.ClothingWarehouseResponse;
import com.example.footballclubapi.entity.ClothSize;
import com.example.footballclubapi.entity.Clothing;
import com.example.footballclubapi.entity.ClothingWarehouse;
import com.example.footballclubapi.service.ClothSizeService;
import com.example.footballclubapi.service.ClothingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClothingWarehouseMapper {

    private final ClothingService clothingService;
    private final ClothSizeService clothSizeService;
    private final ClothingMapper clothingMapper;
    private final ClothSizeMapper clothSizeMapper;

    public ClothingWarehouse toEntity(ClothingWarehouseRequest request) {
        Clothing clothing = clothingService.getById(request.clothing());
        ClothSize size = clothSizeService.getById(request.size());

        return ClothingWarehouse.builder()
                .clothing(clothing)
                .size(size)
                .amount(request.amount())
                .build();
    }

    public ClothingWarehouse toEntity(Long id, ClothingWarehouseRequest request) {
        ClothingWarehouse warehouse = toEntity(request);
        warehouse.setId(id);
        return warehouse;
    }

    public ClothingWarehouseResponse toResponse(ClothingWarehouse clothingWarehouse) {
        ClothingResponse clothing = clothingMapper.toResponse(clothingWarehouse.getClothing());
        ClothSizeResponse size = clothSizeMapper.toResponse(clothingWarehouse.getSize());

        return ClothingWarehouseResponse.builder()
                .clothing(clothing)
                .size(size)
                .amount(clothingWarehouse.getAmount())
                .build();
    }

    public ClothingWarehouseListResponse toListResponse(List<ClothingWarehouse> warehouseList) {
        List<ClothingWarehouseResponse> clothingWarehouseResponseList = warehouseList.stream()
                .map(this::toResponse)
                .toList();
        return ClothingWarehouseListResponse.builder()
                .items(clothingWarehouseResponseList)
                .build();
    }
}

