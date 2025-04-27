package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.ShoesWarehouse;

import java.util.List;

public interface ShoesWarehouseService {
    ShoesWarehouse getById(Long id);
    ShoesWarehouse getByShoesIdAndSizeId(Long shoesId, Long sizeId);
    List<ShoesWarehouse> getAll();
    ShoesWarehouse save(ShoesWarehouse shoesWarehouse);
    ShoesWarehouse update(ShoesWarehouse shoesWarehouse);
    void delete(Long id);
}
