package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.OrderShoes;
import com.example.footballclubapi.entity.ShoesWarehouse;
import com.example.footballclubapi.repository.OrderShoesRepository;
import com.example.footballclubapi.service.OrderShoesService;
import com.example.footballclubapi.service.ShoesWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderShoesServiceImpl implements OrderShoesService {
    private final OrderShoesRepository orderShoesRepository;
    private final ShoesWarehouseService shoesWarehouseService;

    @Override
    public List<OrderShoes> getAll(Long orderId) {
        return orderShoesRepository.findOrderShoesByOrderId(orderId);
    }

    @Transactional
    @Override
    public OrderShoes save(OrderShoes orderShoes) {
        changeAmount(orderShoes.getShoesWarehouse(), orderShoes.getAmount());
        return orderShoesRepository.save(orderShoes);
    }

    @Override
    public void deleteOrderShoesByOrderId(Long orderId) {
        rollbackAmountOfShoes(orderId);
        orderShoesRepository.deleteOrderShoesByOrderId(orderId);
    }

    private void rollbackAmountOfShoes(Long orderId) {
        for (OrderShoes orderShoes : orderShoesRepository.findOrderShoesByOrderId(orderId)) {
            changeAmountAfterDeleteOrder(orderShoes.getShoesWarehouse(), orderShoes.getAmount());
        }
    }

    private void changeAmount(ShoesWarehouse shoesWarehouse, Integer amount) {
        shoesWarehouse.setAmount(shoesWarehouse.getAmount() - amount);
        shoesWarehouseService.update(shoesWarehouse);
    }

    private void changeAmountAfterDeleteOrder(ShoesWarehouse shoesWarehouse, Integer amount) {
        shoesWarehouse.setAmount(shoesWarehouse.getAmount() + amount);
        shoesWarehouseService.update(shoesWarehouse);
    }
}

