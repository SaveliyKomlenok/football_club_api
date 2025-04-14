package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.clothing.ClothingRequest;
import com.example.footballclubapi.dto.response.clothing.ClothingListResponse;
import com.example.footballclubapi.dto.response.clothing.ClothingResponse;
import com.example.footballclubapi.entity.Clothing;
import com.example.footballclubapi.entity.ClothingWarehouse;
import com.example.footballclubapi.mapper.ClothingMapper;
import com.example.footballclubapi.service.ClothingService;
import com.example.footballclubapi.service.ImageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clothing")
//@SecurityRequirement(name = "BearerAuth")
public class ClothingController {
    private final ClothingService clothingService;
    private final ImageService imageService;
    private final ClothingMapper clothingMapper;

    @GetMapping
    public ResponseEntity<ClothingListResponse> getAll() {
        List<Clothing> clothingList = clothingService.getAll();
        return new ResponseEntity<>(clothingMapper.toListResponse(clothingList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingResponse> getById(@PathVariable Long id) {
        Clothing clothing = clothingService.getById(id);
        return new ResponseEntity<>(clothingMapper.toResponse(clothing), HttpStatus.OK);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getClothingImage(@PathVariable Long id) {
        String imagePath = clothingService.getImagePath(id);
        byte[] imageBytes = imageService.getImage(imagePath);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }

    @PostMapping
    public ResponseEntity<ClothingResponse> save(@RequestBody @Valid ClothingRequest request) {
        Clothing clothing = clothingService.save(clothingMapper.toEntity(request));
        return new ResponseEntity<>(clothingMapper.toResponse(clothing), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClothingResponse> update(@PathVariable Long id, @RequestBody @Valid ClothingRequest request) {
        Clothing clothing = clothingService.update(clothingMapper.toEntity(id, request));
        return new ResponseEntity<>(clothingMapper.toResponse(clothing), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clothingService.delete(id);
        return ResponseEntity.ok(String.format("Clothing with id %d removed", id));
    }
}

