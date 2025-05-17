package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.shoes.ShoesRequest;
import com.example.footballclubapi.dto.response.clothing.ClothingWithSizesListResponse;
import com.example.footballclubapi.dto.response.manufacturer.ManufacturerResponse;
import com.example.footballclubapi.dto.response.shoes.ShoesListResponse;
import com.example.footballclubapi.dto.response.shoes.ShoesResponse;
import com.example.footballclubapi.dto.response.shoes.ShoesWithSizesListResponse;
import com.example.footballclubapi.dto.response.shoes.ShoesWithSizesResponse;
import com.example.footballclubapi.dto.response.shoestype.ShoesTypeResponse;
import com.example.footballclubapi.entity.Manufacturer;
import com.example.footballclubapi.entity.Shoes;
import com.example.footballclubapi.entity.ShoesType;
import com.example.footballclubapi.service.ManufacturerService;
import com.example.footballclubapi.service.ShoesTypeService;
import com.example.footballclubapi.service.ShoesWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShoesMapper {
    private final ManufacturerService manufacturerService;
    private final ShoesTypeService shoesTypeService;
    private final ManufacturerMapper manufacturerMapper;
    private final ShoesTypeMapper shoesTypeMapper;
    private final ShoesWarehouseService shoesWarehouseService;

    public Shoes toEntity(ShoesRequest request) {
        Manufacturer manufacturer = manufacturerService.getById(request.manufacturer());
        ShoesType shoesType = shoesTypeService.getById(request.shoesType());

        return Shoes.builder()
                .name(request.name())
                .price(request.price())
                .color(request.color())
                .material(request.material())
                .soleType(request.soleType())
                .description(request.description())
                .imagePath(request.imagePath())
                .manufacturer(manufacturer)
                .shoesType(shoesType)
                .build();
    }

    public Shoes toEntity(Long id, ShoesRequest request) {
        Shoes shoes = toEntity(request);
        shoes.setId(id);
        return shoes;
    }

    public ShoesResponse toResponse(Shoes shoes) {
        ManufacturerResponse manufacturer = manufacturerMapper.toResponse(shoes.getManufacturer());
        ShoesTypeResponse shoesType = shoesTypeMapper.toResponse(shoes.getShoesType());

        return ShoesResponse.builder()
                .id(shoes.getId())
                .name(shoes.getName())
                .price(shoes.getPrice())
                .color(shoes.getColor())
                .material(shoes.getMaterial())
                .soleType(shoes.getSoleType())
                .description(shoes.getDescription())
                .manufacturer(manufacturer)
                .shoesType(shoesType)
                .build();
    }

    public ShoesWithSizesResponse toResponseWithSizes(Shoes shoes) {
        return ShoesWithSizesResponse.builder()
                .shoes(toResponse(shoes))
                .sizes(shoesWarehouseService.getAllShoeSizes(shoes.getId()))
                .build();
    }

    public ShoesListResponse toListResponse(List<Shoes> shoesList) {
        List<ShoesResponse> responses = shoesList.stream()
                .map(this::toResponse)
                .toList();
        return ShoesListResponse.builder()
                .items(responses)
                .build();
    }

    public ShoesWithSizesListResponse toListResponseWithSizes() {
        return ShoesWithSizesListResponse.builder()
                .items(shoesWarehouseService.getAllShoesWithSizes())
                .build();
    }
}
