package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.CartClothing;
import com.example.footballclubapi.entity.Clothing;
import com.example.footballclubapi.entity.ClothingWarehouse;
import com.example.footballclubapi.exception.AmountOfClothingExceededException;
import com.example.footballclubapi.exception.CartClothingNotExistsException;
import com.example.footballclubapi.repository.CartClothingRepository;
import com.example.footballclubapi.service.CartClothingService;
import com.example.footballclubapi.service.ClothingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.footballclubapi.util.ExceptionMessages.AMOUNT_CLOTHING_EXCEEDED;
import static com.example.footballclubapi.util.ExceptionMessages.CART_CLOTHING_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class CartClothingServiceImpl implements CartClothingService {
    private final CartClothingRepository cartClothingRepository;

    @Override
    public CartClothing getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public List<CartClothing> getAll(Long userId) {
        return cartClothingRepository.findCartClothingByUserId(userId);
    }

    @Transactional
    @Override
    public CartClothing save(CartClothing cartClothing) {
        Optional<CartClothing> existingCartClothing = cartClothingRepository
                .findCartClothingByClothingWarehouseIdAndUserId(cartClothing.getClothingWarehouse().getId(),
                        cartClothing.getUser().getId());

        if (existingCartClothing.isPresent()) {
            cartClothing.setId(existingCartClothing.get().getId());
            cartClothing.setAmount(existingCartClothing.get().getAmount() + cartClothing.getAmount());
        }

        if (isEnoughAmount(cartClothing.getClothingWarehouse(), cartClothing)) {
            throw new AmountOfClothingExceededException(AMOUNT_CLOTHING_EXCEEDED);
        }

        return cartClothingRepository.save(cartClothing);
    }

    @Transactional
    @Override
    public CartClothing update(CartClothing cartClothing) {
        getOrThrow(cartClothing.getId());
        if (isEnoughAmount(cartClothing.getClothingWarehouse(), cartClothing)) {
            throw new AmountOfClothingExceededException(AMOUNT_CLOTHING_EXCEEDED);
        }
        return cartClothingRepository.save(cartClothing);
    }

    public boolean isEnoughAmount(ClothingWarehouse clothingWarehouse, CartClothing cartClothing) {
        return cartClothing.getAmount() > clothingWarehouse.getAmount();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getOrThrow(id);
        cartClothingRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteCartByUserId(Long userId) {
        cartClothingRepository.deleteAllByUserId(userId);
    }

    private CartClothing getOrThrow(Long id) {
        return cartClothingRepository.findById(id)
                .orElseThrow(() -> new CartClothingNotExistsException(String.format(CART_CLOTHING_NOT_EXISTS, id)));
    }
}