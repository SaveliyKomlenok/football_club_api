package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.ClothingWarehouse;

import java.util.List;

public interface ClothingWarehouseService {
    ClothingWarehouse getById(Long id);
    ClothingWarehouse getByClothingIdAndSizeId(Long clothingId, Long sizeId);
    List<ClothingWarehouse> getAll();
    ClothingWarehouse save(ClothingWarehouse clothingWarehouse);
    ClothingWarehouse update(ClothingWarehouse clothingWarehouse);
    void delete(Long id);
}
