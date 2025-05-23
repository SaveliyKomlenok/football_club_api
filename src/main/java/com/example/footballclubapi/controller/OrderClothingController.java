package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.response.orderclothing.OrderClothingListResponse;
import com.example.footballclubapi.entity.OrderClothing;
import com.example.footballclubapi.mapper.OrderClothingMapper;
import com.example.footballclubapi.service.OrderClothingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.footballclubapi.util.Constants.CROSS_LOCALHOST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-clothing")
@SecurityRequirement(name = "BearerAuth")
@CrossOrigin(origins = CROSS_LOCALHOST)
public class OrderClothingController {
    private final OrderClothingService orderClothingService;
    private final OrderClothingMapper orderClothingMapper;

    @GetMapping("/{id}")
    public ResponseEntity<OrderClothingListResponse> findAllById(@PathVariable("id") Long id){
        List<OrderClothing> orderClothingList = orderClothingService.getAll(id);
        return new ResponseEntity<>(orderClothingMapper.toListResponse(orderClothingList), HttpStatus.OK);
    }
}
