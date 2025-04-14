package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.manufacturer.ManufacturerRequest;
import com.example.footballclubapi.dto.response.manufacturer.ManufacturerListResponse;
import com.example.footballclubapi.dto.response.manufacturer.ManufacturerResponse;
import com.example.footballclubapi.entity.Manufacturer;
import com.example.footballclubapi.mapper.ManufacturerMapper;
import com.example.footballclubapi.service.ManufacturerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manufacturers")
//@SecurityRequirement(name = "BearerAuth")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;
    private final ManufacturerMapper manufacturerMapper;

    @GetMapping
    public ResponseEntity<ManufacturerListResponse> getAll() {
        List<Manufacturer> manufacturers = manufacturerService.getAll();
        return new ResponseEntity<>(manufacturerMapper.toListResponse(manufacturers), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerResponse> getById(@PathVariable Long id) {
        Manufacturer manufacturer = manufacturerService.getById(id);
        return new ResponseEntity<>(manufacturerMapper.toResponse(manufacturer), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ManufacturerResponse> save(@RequestBody @Valid ManufacturerRequest request) {
        Manufacturer manufacturer = manufacturerService.save(manufacturerMapper.toEntity(request));
        return new ResponseEntity<>(manufacturerMapper.toResponse(manufacturer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturerResponse> update(@PathVariable Long id, @RequestBody @Valid ManufacturerRequest request) {
        Manufacturer manufacturer = manufacturerService.update(manufacturerMapper.toEntity(id, request));
        return new ResponseEntity<>(manufacturerMapper.toResponse(manufacturer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        manufacturerService.delete(id);
        return ResponseEntity.ok(String.format("Manufacturer with id %d removed", id));
    }
}
