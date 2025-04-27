package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.ClothingWarehouse;
import com.example.footballclubapi.exception.ClothingWarehouseNotExistsException;
import com.example.footballclubapi.exception.ClothingWithSelectedSizeNotExistsException;
import com.example.footballclubapi.repository.ClothingWarehouseRepository;
import com.example.footballclubapi.service.ClothingWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.CLOTHING_WAREHOUSE_NOT_EXISTS;
import static com.example.footballclubapi.util.ExceptionMessages.CLOTHING_WITH_SELECTED_SIZE_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ClothingWarehouseServiceImpl implements ClothingWarehouseService {
    private final ClothingWarehouseRepository clothingWarehouseRepository;

    @Override
    public ClothingWarehouse getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public ClothingWarehouse getByClothingIdAndSizeId(Long clothingId, Long sizeId) {
        return clothingWarehouseRepository.findClothingWarehouseByClothingIdAndSizeId(clothingId, sizeId)
                .orElseThrow(() -> new ClothingWithSelectedSizeNotExistsException(String.format(CLOTHING_WITH_SELECTED_SIZE_NOT_EXISTS, clothingId, sizeId)));
    }

    @Override
    public List<ClothingWarehouse> getAll() {
        return clothingWarehouseRepository.findAll();
    }

    @Transactional
    @Override
    public ClothingWarehouse save(ClothingWarehouse clothingWarehouse) {
        return clothingWarehouseRepository.save(clothingWarehouse);
    }

    @Transactional
    @Override
    public ClothingWarehouse update(ClothingWarehouse clothingWarehouse) {
        getOrThrow(clothingWarehouse.getId());
        return clothingWarehouseRepository.save(clothingWarehouse);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getOrThrow(id);
        clothingWarehouseRepository.deleteById(id);
    }

    private ClothingWarehouse getOrThrow(Long id) {
        return clothingWarehouseRepository.findById(id)
                .orElseThrow(() -> new ClothingWarehouseNotExistsException(String.format(CLOTHING_WAREHOUSE_NOT_EXISTS, id)));
    }
}
