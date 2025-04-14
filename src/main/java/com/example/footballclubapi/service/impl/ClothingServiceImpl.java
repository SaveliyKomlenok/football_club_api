package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.Clothing;
import com.example.footballclubapi.exception.ClothingNotExistsException;
import com.example.footballclubapi.repository.ClothingRepository;
import com.example.footballclubapi.service.ClothingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.CLOTHING_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ClothingServiceImpl implements ClothingService {

    private final ClothingRepository clothingRepository;

    @Override
    public Clothing getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public List<Clothing> getAll() {
        return clothingRepository.findAll();
    }

    @Transactional
    @Override
    public Clothing save(Clothing clothing) {
        return clothingRepository.save(clothing);
    }

    @Transactional
    @Override
    public Clothing update(Clothing clothing) {
        getOrThrow(clothing.getId());
        return clothingRepository.save(clothing);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getOrThrow(id);
        clothingRepository.deleteById(id);
    }

    @Override
    public String getImagePath(Long id) {
        return getOrThrow(id).getImagePath();
    }

    private Clothing getOrThrow(Long id) {
        return clothingRepository.findById(id)
                .orElseThrow(() -> new ClothingNotExistsException(String.format(CLOTHING_NOT_EXISTS, id)));
    }
}
