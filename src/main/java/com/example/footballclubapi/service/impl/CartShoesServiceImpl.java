package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.CartShoes;
import com.example.footballclubapi.entity.ShoesWarehouse;
import com.example.footballclubapi.exception.AmountOfShoesExceededException;
import com.example.footballclubapi.exception.CartShoesNotExistsException;
import com.example.footballclubapi.repository.CartShoesRepository;
import com.example.footballclubapi.service.CartShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.footballclubapi.util.ExceptionMessages.AMOUNT_SHOES_EXCEEDED;
import static com.example.footballclubapi.util.ExceptionMessages.CART_SHOES_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class CartShoesServiceImpl implements CartShoesService {
    private final CartShoesRepository cartShoesRepository;

    @Override
    public CartShoes getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public List<CartShoes> getAll(Long userId) {
        return cartShoesRepository.findCartShoesByUserId(userId);
    }

    @Transactional
    @Override
    public CartShoes save(CartShoes cartShoes) {
        Optional<CartShoes> existingCartShoes = cartShoesRepository
                .findCartShoesByShoesWarehouseIdAndUserId(cartShoes.getShoesWarehouse().getId(),
                        cartShoes.getUser().getId());

        if (existingCartShoes.isPresent()) {
            cartShoes.setId(existingCartShoes.get().getId());
            cartShoes.setAmount(existingCartShoes.get().getAmount() + cartShoes.getAmount());
        }

        if (isEnoughAmount(cartShoes.getShoesWarehouse(), cartShoes)) {
            throw new AmountOfShoesExceededException(AMOUNT_SHOES_EXCEEDED);
        }

        return cartShoesRepository.save(cartShoes);
    }

    @Transactional
    @Override
    public CartShoes update(CartShoes cartShoes) {
        getOrThrow(cartShoes.getId());
        if (isEnoughAmount(cartShoes.getShoesWarehouse(), cartShoes)) {
            throw new AmountOfShoesExceededException(AMOUNT_SHOES_EXCEEDED);
        }
        return cartShoesRepository.save(cartShoes);
    }

    public boolean isEnoughAmount(ShoesWarehouse shoesWarehouse, CartShoes cartShoes) {
        return cartShoes.getAmount() > shoesWarehouse.getAmount();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getOrThrow(id);
        cartShoesRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteCartByUserId(Long userId) {
        cartShoesRepository.deleteAllByUserId(userId);
    }

    private CartShoes getOrThrow(Long id) {
        return cartShoesRepository.findById(id)
                .orElseThrow(() -> new CartShoesNotExistsException(String.format(CART_SHOES_NOT_EXISTS, id)));
    }
}