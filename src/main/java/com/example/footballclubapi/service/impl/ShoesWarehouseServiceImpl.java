package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.dto.response.clothing.ClothingResponse;
import com.example.footballclubapi.dto.response.clothing.ClothingWithSizesResponse;
import com.example.footballclubapi.dto.response.clothingtype.ClothingTypeResponse;
import com.example.footballclubapi.dto.response.manufacturer.ManufacturerResponse;
import com.example.footballclubapi.dto.response.shoes.ShoesResponse;
import com.example.footballclubapi.dto.response.shoes.ShoesWithSizesResponse;
import com.example.footballclubapi.dto.response.shoesize.ShoeSizeWithAmountResponse;
import com.example.footballclubapi.dto.response.shoestype.ShoesTypeResponse;
import com.example.footballclubapi.entity.Shoes;
import com.example.footballclubapi.entity.ShoesWarehouse;
import com.example.footballclubapi.exception.ClothingWithSelectedSizeNotExistsException;
import com.example.footballclubapi.exception.ShoesWarehouseNotExistsException;
import com.example.footballclubapi.repository.ShoesWarehouseRepository;
import com.example.footballclubapi.service.ShoesService;
import com.example.footballclubapi.service.ShoesWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.CLOTHING_WITH_SELECTED_SIZE_NOT_EXISTS;
import static com.example.footballclubapi.util.ExceptionMessages.SHOES_WAREHOUSE_NOT_EXISTS;
import static com.example.footballclubapi.util.ExceptionMessages.SHOES_WITH_SELECTED_SIZE_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ShoesWarehouseServiceImpl implements ShoesWarehouseService {
    private final ShoesWarehouseRepository shoesWarehouseRepository;
    private final ShoesService shoesService;

    @Override
    public ShoesWarehouse getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public ShoesWarehouse getByShoesIdAndSizeId(Long shoesId, Long sizeId) {
        return shoesWarehouseRepository.findShoesWarehouseByShoesIdAndSizeId(shoesId, sizeId)
                .orElseThrow(() -> new ClothingWithSelectedSizeNotExistsException(String.format(SHOES_WITH_SELECTED_SIZE_NOT_EXISTS, shoesId, sizeId)));
    }

    @Override
    public List<ShoeSizeWithAmountResponse> getAllShoeSizes(Long shoesId) {
        return shoesWarehouseRepository.findShoesWarehousesByShoesId(shoesId).stream()
                .map(shoesWarehouse -> ShoeSizeWithAmountResponse.builder()
                        .id(shoesWarehouse.getSize().getId())
                        .size(shoesWarehouse.getSize().getSize())
                        .amount(shoesWarehouse.getAmount())
                        .build())
                .toList();
    }

    @Override
    public List<ShoesWithSizesResponse> getAllShoesWithSizes() {
        return shoesService.getAll().stream()
                .map(shoes -> ShoesWithSizesResponse.builder()
                        .shoes(ShoesResponse.builder()
                                .id(shoes.getId())
                                .name(shoes.getName())
                                .price(shoes.getPrice())
                                .color(shoes.getColor())
                                .material(shoes.getMaterial())
                                .soleType(shoes.getSoleType())
                                .description(shoes.getDescription())
                                .manufacturer(ManufacturerResponse.builder()
                                        .id(shoes.getManufacturer().getId())
                                        .name(shoes.getManufacturer().getName())
                                        .build())
                                .shoesType(ShoesTypeResponse.builder()
                                        .id(shoes.getShoesType().getId())
                                        .name(shoes.getShoesType().getName())
                                        .build())
                                .build())
                        .sizes(getAllShoeSizes(shoes.getId()))
                        .build()).toList();
    }

    @Override
    public List<ShoesWarehouse> getAll() {
        return shoesWarehouseRepository.findAll();
    }

    @Transactional
    @Override
    public ShoesWarehouse save(ShoesWarehouse shoesWarehouse) {
        return shoesWarehouseRepository.save(shoesWarehouse);
    }

    @Transactional
    @Override
    public ShoesWarehouse update(ShoesWarehouse shoesWarehouse) {
        getOrThrow(shoesWarehouse.getId());
        return shoesWarehouseRepository.save(shoesWarehouse);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getOrThrow(id);
        shoesWarehouseRepository.deleteById(id);
    }

    private ShoesWarehouse getOrThrow(Long id) {
        return shoesWarehouseRepository.findById(id)
                .orElseThrow(() -> new ShoesWarehouseNotExistsException(String.format(SHOES_WAREHOUSE_NOT_EXISTS, id)));
    }
}
