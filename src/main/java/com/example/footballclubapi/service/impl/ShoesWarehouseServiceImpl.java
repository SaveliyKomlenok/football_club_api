package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.ShoesWarehouse;
import com.example.footballclubapi.exception.ShoesWarehouseNotExistsException;
import com.example.footballclubapi.repository.ShoesWarehouseRepository;
import com.example.footballclubapi.service.ShoesWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.SHOES_WAREHOUSE_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ShoesWarehouseServiceImpl implements ShoesWarehouseService {

    private final ShoesWarehouseRepository shoesWarehouseRepository;

    @Override
    public ShoesWarehouse getById(Long id) {
        return getOrThrow(id);
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
