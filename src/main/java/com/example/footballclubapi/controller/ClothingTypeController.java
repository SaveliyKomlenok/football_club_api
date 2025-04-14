package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.clothingtype.ClothingTypeRequest;
import com.example.footballclubapi.dto.response.clothingtype.ClothingTypeListResponse;
import com.example.footballclubapi.dto.response.clothingtype.ClothingTypeResponse;
import com.example.footballclubapi.entity.ClothingType;
import com.example.footballclubapi.mapper.ClothingTypeMapper;
import com.example.footballclubapi.service.ClothingTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clothing-types")
//@SecurityRequirement(name = "BearerAuth")
public class ClothingTypeController {
    private final ClothingTypeService clothingTypeService;
    private final ClothingTypeMapper clothingTypeMapper;

    @GetMapping
    public ResponseEntity<ClothingTypeListResponse> getAll() {
        List<ClothingType> clothingTypeList = clothingTypeService.getAll();
        return new ResponseEntity<>(clothingTypeMapper.toListResponse(clothingTypeList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingTypeResponse> getById(@PathVariable Long id) {
        ClothingType clothingType = clothingTypeService.getById(id);
        return new ResponseEntity<>(clothingTypeMapper.toResponse(clothingType), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClothingTypeResponse> save(@RequestBody @Valid ClothingTypeRequest request) {
        ClothingType clothingType = clothingTypeService.save(clothingTypeMapper.toEntity(request));
        return new ResponseEntity<>(clothingTypeMapper.toResponse(clothingType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClothingTypeResponse> update(@PathVariable Long id, @RequestBody @Valid ClothingTypeRequest request) {
        ClothingType clothingType = clothingTypeService.update(clothingTypeMapper.toEntity(id, request));
        return new ResponseEntity<>(clothingTypeMapper.toResponse(clothingType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clothingTypeService.delete(id);
        return ResponseEntity.ok(String.format("Clothing type with id %d removed", id));
    }
}

