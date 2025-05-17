package com.example.footballclubapi.service;

import com.example.footballclubapi.dto.response.clothing.ClothingWithSizesResponse;
import com.example.footballclubapi.dto.response.clothsize.ClothSizeResponse;
import com.example.footballclubapi.dto.response.clothsize.ClothSizeWithAmountResponse;
import com.example.footballclubapi.entity.ClothingWarehouse;

import java.util.HashMap;
import java.util.List;

public interface ClothingWarehouseService {
    ClothingWarehouse getById(Long id);
    ClothingWarehouse getByClothingIdAndSizeId(Long clothingId, Long sizeId);
    List<ClothSizeWithAmountResponse> getAllClothSizes(Long clothingId);
    List<ClothingWithSizesResponse> getAllClothingWithSizes();
    List<ClothingWarehouse> getAll();
    ClothingWarehouse save(ClothingWarehouse clothingWarehouse);
    ClothingWarehouse update(ClothingWarehouse clothingWarehouse);
    void delete(Long id);
}
