package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.shoeswarehouse.ShoesWarehouseRequest;
import com.example.footballclubapi.dto.response.shoeswarehouse.ShoesWarehouseListResponse;
import com.example.footballclubapi.dto.response.shoeswarehouse.ShoesWarehouseResponse;
import com.example.footballclubapi.entity.ShoesWarehouse;
import com.example.footballclubapi.mapper.ShoesWarehouseMapper;
import com.example.footballclubapi.service.ShoesWarehouseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shoes-warehouses")
//@SecurityRequirement(name = "BearerAuth")
public class ShoesWarehouseController {
    private final ShoesWarehouseService shoesWarehouseService;
    private final ShoesWarehouseMapper shoesWarehouseMapper;

    @GetMapping
    public ResponseEntity<ShoesWarehouseListResponse> getAll() {
        List<ShoesWarehouse> warehouses = shoesWarehouseService.getAll();
        return new ResponseEntity<>(shoesWarehouseMapper.toListResponse(warehouses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoesWarehouseResponse> getById(@PathVariable Long id) {
        ShoesWarehouse warehouse = shoesWarehouseService.getById(id);
        return new ResponseEntity<>(shoesWarehouseMapper.toResponse(warehouse), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShoesWarehouseResponse> save(@RequestBody @Valid ShoesWarehouseRequest request) {
        ShoesWarehouse warehouse = shoesWarehouseService.save(shoesWarehouseMapper.toEntity(request));
        return new ResponseEntity<>(shoesWarehouseMapper.toResponse(warehouse), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoesWarehouseResponse> update(@PathVariable Long id, @RequestBody @Valid ShoesWarehouseRequest request) {
        ShoesWarehouse warehouse = shoesWarehouseService.update(shoesWarehouseMapper.toEntity(id, request));
        return new ResponseEntity<>(shoesWarehouseMapper.toResponse(warehouse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        shoesWarehouseService.delete(id);
        return ResponseEntity.ok(String.format("Shoes warehouse with id %d removed", id));
    }
}
