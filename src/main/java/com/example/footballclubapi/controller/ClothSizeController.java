package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.clothsize.ClothSizeRequest;
import com.example.footballclubapi.dto.response.clothsize.ClothSizeListResponse;
import com.example.footballclubapi.dto.response.clothsize.ClothSizeResponse;
import com.example.footballclubapi.entity.ClothSize;
import com.example.footballclubapi.mapper.ClothSizeMapper;
import com.example.footballclubapi.service.ClothSizeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cloth-sizes")
//@SecurityRequirement(name = "BearerAuth")
public class ClothSizeController {
    private final ClothSizeService clothSizeService;
    private final ClothSizeMapper clothSizeMapper;

    @GetMapping
    public ResponseEntity<ClothSizeListResponse> getAll() {
        List<ClothSize> sizes = clothSizeService.getAll();
        return new ResponseEntity<>(clothSizeMapper.toListResponse(sizes), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothSizeResponse> getById(@PathVariable Long id) {
        ClothSize size = clothSizeService.getById(id);
        return new ResponseEntity<>(clothSizeMapper.toResponse(size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClothSizeResponse> save(@RequestBody @Valid ClothSizeRequest request) {
        ClothSize size = clothSizeService.save(clothSizeMapper.toEntity(request));
        return new ResponseEntity<>(clothSizeMapper.toResponse(size), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClothSizeResponse> update(@PathVariable Long id, @RequestBody @Valid ClothSizeRequest request) {
        ClothSize size = clothSizeService.update(clothSizeMapper.toEntity(id, request));
        return new ResponseEntity<>(clothSizeMapper.toResponse(size), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clothSizeService.delete(id);
        return ResponseEntity.ok(String.format("Cloth size with id %d removed", id));
    }
}