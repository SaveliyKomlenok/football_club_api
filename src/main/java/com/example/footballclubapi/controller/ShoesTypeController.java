package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.shoestype.ShoesTypeRequest;
import com.example.footballclubapi.dto.response.shoestype.ShoesTypeListResponse;
import com.example.footballclubapi.dto.response.shoestype.ShoesTypeResponse;
import com.example.footballclubapi.entity.ShoesType;
import com.example.footballclubapi.mapper.ShoesTypeMapper;
import com.example.footballclubapi.service.ShoesTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shoes-types")
//@SecurityRequirement(name = "BearerAuth")
public class ShoesTypeController {

    private final ShoesTypeService shoesTypeService;
    private final ShoesTypeMapper shoesTypeMapper;

    @GetMapping
    public ResponseEntity<ShoesTypeListResponse> getAll() {
        List<ShoesType> shoesTypeList = shoesTypeService.getAll();
        return new ResponseEntity<>(shoesTypeMapper.toListResponse(shoesTypeList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoesTypeResponse> getById(@PathVariable Long id) {
        ShoesType shoesType = shoesTypeService.getById(id);
        return new ResponseEntity<>(shoesTypeMapper.toResponse(shoesType), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShoesTypeResponse> save(@RequestBody @Valid ShoesTypeRequest request) {
        ShoesType shoesType = shoesTypeService.save(shoesTypeMapper.toEntity(request));
        return new ResponseEntity<>(shoesTypeMapper.toResponse(shoesType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoesTypeResponse> update(@PathVariable Long id, @RequestBody @Valid ShoesTypeRequest request) {
        ShoesType shoesType = shoesTypeService.update(shoesTypeMapper.toEntity(id, request));
        return new ResponseEntity<>(shoesTypeMapper.toResponse(shoesType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        shoesTypeService.delete(id);
        return ResponseEntity.ok(String.format("Shoes type with id %d removed", id));
    }
}
