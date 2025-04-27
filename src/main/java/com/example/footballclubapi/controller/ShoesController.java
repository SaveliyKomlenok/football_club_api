package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.shoes.ShoesRequest;
import com.example.footballclubapi.dto.response.shoes.ShoesListResponse;
import com.example.footballclubapi.dto.response.shoes.ShoesResponse;
import com.example.footballclubapi.entity.Shoes;
import com.example.footballclubapi.mapper.ShoesMapper;
import com.example.footballclubapi.service.ShoesService;
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
@RequestMapping("/api/v1/shoes")
@SecurityRequirement(name = "BearerAuth")
public class ShoesController {
    private final ShoesService shoesService;
    private final ImageService imageService;
    private final ShoesMapper shoesMapper;

    @GetMapping
    public ResponseEntity<ShoesListResponse> getAll() {
        List<Shoes> shoesList = shoesService.getAll();
        return new ResponseEntity<>(shoesMapper.toListResponse(shoesList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoesResponse> getById(@PathVariable Long id) {
        Shoes shoes = shoesService.getById(id);
        return new ResponseEntity<>(shoesMapper.toResponse(shoes), HttpStatus.OK);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getShoesImage(@PathVariable Long id) {
        String imagePath = shoesService.getImagePath(id);
        byte[] imageBytes = imageService.getImage(imagePath);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }

    @PostMapping
    public ResponseEntity<ShoesResponse> save(@RequestBody @Valid ShoesRequest request) {
        Shoes shoes = shoesService.save(shoesMapper.toEntity(request));
        return new ResponseEntity<>(shoesMapper.toResponse(shoes), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoesResponse> update(@PathVariable Long id, @RequestBody @Valid ShoesRequest request) {
        Shoes shoes = shoesService.update(shoesMapper.toEntity(id, request));
        return new ResponseEntity<>(shoesMapper.toResponse(shoes), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        shoesService.delete(id);
        return ResponseEntity.ok(String.format("Shoes with id %d removed", id));
    }
}