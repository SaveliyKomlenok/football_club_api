package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.clothingwarehouse.ClothingWarehouseRequest;
import com.example.footballclubapi.dto.response.clothingwarehouse.ClothingWarehouseListResponse;
import com.example.footballclubapi.dto.response.clothingwarehouse.ClothingWarehouseResponse;
import com.example.footballclubapi.entity.ClothingWarehouse;
import com.example.footballclubapi.mapper.ClothingWarehouseMapper;
import com.example.footballclubapi.service.ClothingWarehouseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clothing-warehouses")
@SecurityRequirement(name = "BearerAuth")
public class ClothingWarehouseController {
    private final ClothingWarehouseService clothingWarehouseService;
    private final ClothingWarehouseMapper clothingWarehouseMapper;

    @GetMapping
    public ResponseEntity<ClothingWarehouseListResponse> getAll() {
        List<ClothingWarehouse> warehouses = clothingWarehouseService.getAll();
        return new ResponseEntity<>(clothingWarehouseMapper.toListResponse(warehouses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingWarehouseResponse> getById(@PathVariable Long id) {
        ClothingWarehouse warehouse = clothingWarehouseService.getById(id);
        return new ResponseEntity<>(clothingWarehouseMapper.toResponse(warehouse), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClothingWarehouseResponse> save(@RequestBody @Valid ClothingWarehouseRequest request) {
        ClothingWarehouse warehouse = clothingWarehouseService.save(clothingWarehouseMapper.toEntity(request));
        return new ResponseEntity<>(clothingWarehouseMapper.toResponse(warehouse), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClothingWarehouseResponse> update(@PathVariable Long id, @RequestBody @Valid ClothingWarehouseRequest request) {
        ClothingWarehouse warehouse = clothingWarehouseService.update(clothingWarehouseMapper.toEntity(id, request));
        return new ResponseEntity<>(clothingWarehouseMapper.toResponse(warehouse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clothingWarehouseService.delete(id);
        return ResponseEntity.ok(String.format("Clothing warehouse with id %d removed", id));
    }
}