package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.*;
import com.example.footballclubapi.exception.OrderNotExistException;
import com.example.footballclubapi.repository.OrderRepository;
import com.example.footballclubapi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.ORDER_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final CartClothingService cartClothingService;
    private final CartShoesService cartShoesService;
    private final OrderClothingService orderClothingService;
    private final OrderShoesService orderShoesService;

    @Override
    public List<Order> getAll(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Transactional
    @Override
    public Order save(Order order) {
        order.setTotalPrice(cartService.getTotalPrice(order.getUser().getId()));
        Order savedOrder = orderRepository.save(order);
        mapCartToOrderClothing(savedOrder);
        mapCartToOrderShoes(savedOrder);
        return savedOrder;
    }

    private void mapCartToOrderClothing(Order order) {
        for (CartClothing cartClothing : cartClothingService.getAll(order.getUser().getId())) {
            OrderClothing orderClothing = OrderClothing.builder()
                    .amount(cartClothing.getAmount())
                    .clothingWarehouse(cartClothing.getClothingWarehouse())
                    .order(order)
                    .build();
            orderClothingService.save(orderClothing);
        }
        cartClothingService.deleteCartByUserId(order.getUser().getId());
    }

    private void mapCartToOrderShoes(Order order) {
        for (CartShoes cartShoes : cartShoesService.getAll(order.getUser().getId())) {
            OrderShoes orderShoes = OrderShoes.builder()
                    .amount(cartShoes.getAmount())
                    .shoesWarehouse(cartShoes.getShoesWarehouse())
                    .order(order)
                    .build();
            orderShoesService.save(orderShoes);
        }
        cartShoesService.deleteCartByUserId(order.getUser().getId());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        orderRepository.findById(id)
                .map(order -> {
                    orderClothingService.deleteOrderClothingByOrderId(id);
                    orderShoesService.deleteOrderShoesByOrderId(id);
                    orderRepository.delete(order);
                    return true;
                })
                .orElseThrow(() -> new OrderNotExistException(String.format(ORDER_NOT_EXISTS, id)));
    }
}

