package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.shoesize.ShoeSizeRequest;
import com.example.footballclubapi.dto.response.shoesize.ShoeSizeListResponse;
import com.example.footballclubapi.dto.response.shoesize.ShoeSizeResponse;
import com.example.footballclubapi.entity.ShoeSize;
import com.example.footballclubapi.mapper.ShoeSizeMapper;
import com.example.footballclubapi.service.ShoeSizeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shoe-sizes")
//@SecurityRequirement(name = "BearerAuth")
public class ShoeSizeController {
    private final ShoeSizeService shoeSizeService;
    private final ShoeSizeMapper shoeSizeMapper;

    @GetMapping
    public ResponseEntity<ShoeSizeListResponse> getAll() {
        List<ShoeSize> sizes = shoeSizeService.getAll();
        return new ResponseEntity<>(shoeSizeMapper.toListResponse(sizes), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoeSizeResponse> getById(@PathVariable Long id) {
        ShoeSize size = shoeSizeService.getById(id);
        return new ResponseEntity<>(shoeSizeMapper.toResponse(size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShoeSizeResponse> save(@RequestBody @Valid ShoeSizeRequest request) {
        ShoeSize size = shoeSizeService.save(shoeSizeMapper.toEntity(request));
        return new ResponseEntity<>(shoeSizeMapper.toResponse(size), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoeSizeResponse> update(@PathVariable Long id, @RequestBody @Valid ShoeSizeRequest request) {
        ShoeSize size = shoeSizeService.update(shoeSizeMapper.toEntity(id, request));
        return new ResponseEntity<>(shoeSizeMapper.toResponse(size), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        shoeSizeService.delete(id);
        return ResponseEntity.ok(String.format("Shoe size with id %d removed", id));
    }
}
