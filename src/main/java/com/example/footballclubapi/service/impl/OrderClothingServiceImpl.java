package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.ClothingWarehouse;
import com.example.footballclubapi.entity.OrderClothing;
import com.example.footballclubapi.repository.OrderClothingRepository;
import com.example.footballclubapi.service.ClothingWarehouseService;
import com.example.footballclubapi.service.OrderClothingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderClothingServiceImpl implements OrderClothingService {
    private final OrderClothingRepository orderClothingRepository;
    private final ClothingWarehouseService clothingWarehouseService;

    @Override
    public List<OrderClothing> getAll(Long orderId) {
        return orderClothingRepository.findOrderClothingsByOrderId(orderId);
    }

    @Transactional
    @Override
    public OrderClothing save(OrderClothing orderClothing) {
        changeAmount(orderClothing.getClothingWarehouse(), orderClothing.getAmount());
        return orderClothingRepository.save(orderClothing);
    }

    @Override
    public void deleteOrderClothingByOrderId(Long orderId) {
        rollbackAmountOfClothing(orderId);
        orderClothingRepository.deleteOrderClothingsByOrderId(orderId);
    }

    private void rollbackAmountOfClothing(Long orderId) {
        for (OrderClothing orderClothing : orderClothingRepository.findOrderClothingsByOrderId(orderId)) {
            changeAmountAfterDeleteOrder(orderClothing.getClothingWarehouse(), orderClothing.getAmount());
        }
    }

    private void changeAmount(ClothingWarehouse clothingWarehouse, Integer amount) {
        clothingWarehouse.setAmount(clothingWarehouse.getAmount() - amount);
        clothingWarehouseService.update(clothingWarehouse);
    }

    private void changeAmountAfterDeleteOrder(ClothingWarehouse clothingWarehouse, Integer amount) {
        clothingWarehouse.setAmount(clothingWarehouse.getAmount() + amount);
        clothingWarehouseService.update(clothingWarehouse);
    }
}

