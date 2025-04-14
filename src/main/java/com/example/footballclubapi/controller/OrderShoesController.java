package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.response.orderclothing.OrderClothingListResponse;
import com.example.footballclubapi.dto.response.ordershoes.OrderShoesListResponse;
import com.example.footballclubapi.entity.OrderClothing;
import com.example.footballclubapi.entity.OrderShoes;
import com.example.footballclubapi.mapper.OrderShoesMapper;
import com.example.footballclubapi.service.OrderShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-shoes")
//@SecurityRequirement(name = "BearerAuth")
public class OrderShoesController {
    private final OrderShoesService orderShoesService;
    private final OrderShoesMapper orderShoesMapper;

    @GetMapping("/{id}")
    public ResponseEntity<OrderShoesListResponse> findAllById(@PathVariable("id") Long id){
        List<OrderShoes> orderShoesList = orderShoesService.getAll(id);
        return new ResponseEntity<>(orderShoesMapper.toListResponse(orderShoesList), HttpStatus.OK);
    }
}
