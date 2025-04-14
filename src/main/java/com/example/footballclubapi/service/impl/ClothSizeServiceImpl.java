package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.ClothSize;
import com.example.footballclubapi.exception.ClothSizeNotExistsException;
import com.example.footballclubapi.repository.ClothSizeRepository;
import com.example.footballclubapi.service.ClothSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.CLOTH_SIZE_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ClothSizeServiceImpl implements ClothSizeService {

    private final ClothSizeRepository clothSizeRepository;

    @Override
    public ClothSize getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public List<ClothSize> getAll() {
        return clothSizeRepository.findAll();
    }

    @Override
    public ClothSize save(ClothSize clothSize) {
        return clothSizeRepository.save(clothSize);
    }

    @Override
    public ClothSize update(ClothSize clothSize) {
        getOrThrow(clothSize.getId());
        return clothSizeRepository.save(clothSize);
    }

    @Override
    public void delete(Long id) {
        getOrThrow(id);
        clothSizeRepository.deleteById(id);
    }

    private ClothSize getOrThrow(Long id) {
        return clothSizeRepository.findById(id)
                .orElseThrow(() -> new ClothSizeNotExistsException(String.format(CLOTH_SIZE_NOT_EXISTS, id)));
    }
}
