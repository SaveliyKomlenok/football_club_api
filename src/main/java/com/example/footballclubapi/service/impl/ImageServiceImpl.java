package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    @SneakyThrows
    @Override
    public byte[] getImage(String imagePath) {
        return Files.readAllBytes(new File(imagePath).toPath());
    }
}
