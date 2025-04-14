package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.ClothingType;
import com.example.footballclubapi.exception.ClothingTypeNotExistsException;
import com.example.footballclubapi.repository.ClothingTypeRepository;
import com.example.footballclubapi.service.ClothingTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.CLOTHING_TYPE_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ClothingTypeServiceImpl implements ClothingTypeService {

    private final ClothingTypeRepository clothingTypeRepository;

    @Override
    public ClothingType getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public List<ClothingType> getAll() {
        return clothingTypeRepository.findAll();
    }

    @Override
    public ClothingType save(ClothingType clothingType) {
        return clothingTypeRepository.save(clothingType);
    }

    @Override
    public ClothingType update(ClothingType clothingType) {
        getOrThrow(clothingType.getId());
        return clothingTypeRepository.save(clothingType);
    }

    @Override
    public void delete(Long id) {
        getOrThrow(id);
        clothingTypeRepository.deleteById(id);
    }

    private ClothingType getOrThrow(Long id) {
        return clothingTypeRepository.findById(id)
                .orElseThrow(() -> new ClothingTypeNotExistsException(String.format(CLOTHING_TYPE_NOT_EXISTS, id)));
    }
}
