package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.shoeswarehouse.ShoesWarehouseRequest;
import com.example.footballclubapi.dto.response.shoes.ShoesResponse;
import com.example.footballclubapi.dto.response.shoesize.ShoeSizeResponse;
import com.example.footballclubapi.dto.response.shoeswarehouse.ShoesWarehouseListResponse;
import com.example.footballclubapi.dto.response.shoeswarehouse.ShoesWarehouseResponse;
import com.example.footballclubapi.entity.Shoes;
import com.example.footballclubapi.entity.ShoeSize;
import com.example.footballclubapi.entity.ShoesWarehouse;
import com.example.footballclubapi.service.ShoeSizeService;
import com.example.footballclubapi.service.ShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShoesWarehouseMapper {

    private final ShoesService shoesService;
    private final ShoeSizeService shoeSizeService;
    private final ShoesMapper shoesMapper;
    private final ShoeSizeMapper shoeSizeMapper;

    public ShoesWarehouse toEntity(ShoesWarehouseRequest request) {
        Shoes shoes = shoesService.getById(request.shoes());
        ShoeSize size = shoeSizeService.getById(request.size());

        return ShoesWarehouse.builder()
                .shoes(shoes)
                .size(size)
                .amount(request.amount())
                .build();
    }

    public ShoesWarehouse toEntity(Long id, ShoesWarehouseRequest request) {
        ShoesWarehouse warehouse = toEntity(request);
        warehouse.setId(id);
        return warehouse;
    }

    public ShoesWarehouseResponse toResponse(ShoesWarehouse shoesWarehouse) {
        ShoesResponse shoes = shoesMapper.toResponse(shoesWarehouse.getShoes());
        ShoeSizeResponse size = shoeSizeMapper.toResponse(shoesWarehouse.getSize());

        return ShoesWarehouseResponse.builder()
                .shoes(shoes)
                .size(size)
                .amount(shoesWarehouse.getAmount())
                .build();
    }

    public ShoesWarehouseListResponse toListResponse(List<ShoesWarehouse> warehouseList) {
        List<ShoesWarehouseResponse> responses = warehouseList.stream()
                .map(this::toResponse)
                .toList();

        return ShoesWarehouseListResponse.builder()
                .items(responses)
                .build();
    }
}
