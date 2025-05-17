package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.clothing.ClothingRequest;
import com.example.footballclubapi.dto.response.clothing.ClothingListResponse;
import com.example.footballclubapi.dto.response.clothing.ClothingResponse;
import com.example.footballclubapi.dto.response.clothing.ClothingWithSizesListResponse;
import com.example.footballclubapi.dto.response.clothing.ClothingWithSizesResponse;
import com.example.footballclubapi.dto.response.clothingtype.ClothingTypeResponse;
import com.example.footballclubapi.dto.response.manufacturer.ManufacturerResponse;
import com.example.footballclubapi.entity.Clothing;
import com.example.footballclubapi.entity.ClothingType;
import com.example.footballclubapi.entity.Manufacturer;
import com.example.footballclubapi.service.ClothingService;
import com.example.footballclubapi.service.ClothingTypeService;
import com.example.footballclubapi.service.ClothingWarehouseService;
import com.example.footballclubapi.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClothingMapper {
    private final ManufacturerService manufacturerService;
    private final ClothingTypeService clothingTypeService;
    private final ClothingService clothingService;
    private final ManufacturerMapper manufacturerMapper;
    private final ClothingTypeMapper clothingTypeMapper;
    private final ClothingWarehouseService clothingWarehouseService;

    public Clothing toEntity(ClothingRequest request) {
        Manufacturer manufacturer = manufacturerService.getById(request.manufacturer());
        ClothingType type = clothingTypeService.getById(request.clothingType());

        return Clothing.builder()
                .name(request.name())
                .price(request.price())
                .color(request.color())
                .material(request.material())
                .description(request.description())
                .imagePath(request.imagePath())
                .manufacturer(manufacturer)
                .clothingType(type)
                .build();
    }

    public Clothing toEntity(Long id, ClothingRequest request) {
        Clothing clothing = toEntity(request);
        clothing.setId(id);
        return clothing;
    }

    public ClothingResponse toResponse(Clothing clothing) {
        ManufacturerResponse manufacturer = manufacturerMapper.toResponse(clothing.getManufacturer());
        ClothingTypeResponse clothingType = clothingTypeMapper.toResponse(clothing.getClothingType());

        return ClothingResponse.builder()
                .id(clothing.getId())
                .name(clothing.getName())
                .price(clothing.getPrice())
                .color(clothing.getColor())
                .material(clothing.getMaterial())
                .description(clothing.getDescription())
                .manufacturer(manufacturer)
                .clothingType(clothingType)
                .build();
    }

    public ClothingWithSizesResponse toResponseWithSizes(Clothing clothing) {
        return ClothingWithSizesResponse.builder()
                .clothing(toResponse(clothing))
                .sizes(clothingWarehouseService.getAllClothSizes(clothing.getId()))
                .build();
    }

    public ClothingListResponse toListResponse(List<Clothing> clothingList) {
        List<ClothingResponse> clothingResponseList = clothingList.stream()
                .map(this::toResponse)
                .toList();
        return ClothingListResponse.builder()
                .items(clothingResponseList)
                .build();
    }

    public ClothingWithSizesListResponse toListResponseWithSizes() {
        return ClothingWithSizesListResponse.builder()
                .items(clothingWarehouseService.getAllClothingWithSizes())
                .build();
    }
}
