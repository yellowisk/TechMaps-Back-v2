package com.acing.techmaps.infrastructure.filestorage;

import com.acing.techmaps.domain.entities.filestorage.StorageType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.UUID;

@Service
public class LocalStorageStrategy implements StorageStrategy {

    private final Path uploadPath;
    private static final Set<String> SUPPORTED_TYPES = Set.of(
        "application/pdf", 
        "application/vnd.openxmlformats-officedocument.presentationml.presentation", // PowerPoint
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // Word
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // Excel
        "pplication/vnd.google-apps.presentation", // Google Slides
        "application/vnd.google-apps.spreadsheet", // Google Sheets
        "application/vnd.google-apps.document" // Google Docs
    );

    public LocalStorageStrategy(@Value("${file.upload-dir}") String uploadDir) {
        this.uploadPath = Paths.get(uploadDir);
        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public boolean supports(String mimeType) {
        return SUPPORTED_TYPES.contains(mimeType);
    }

    @Override
    public boolean canHandle(String fileName) {
        // Checks if the filename pattern matches the date structure yyyy/MM/dd/filename
        return fileName.matches("\\d{4}/\\d{2}/\\d{2}/.*");
    }

    @Override
    public String upload(MultipartFile file) {
        try {
            String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            Path targetPath = this.uploadPath.resolve(datePath);
            Files.createDirectories(targetPath);
            
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                 extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            UUID id = Generators.timeBasedEpochGenerator().generate();
            String fileName = id.toString() + extension;
            
            Path destination = targetPath.resolve(fileName);
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            }
            
            // Return relative path including date for retrieval
            return datePath + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file locally", e);
        }
    }

    @Override
    public Resource download(String fileName) {
        try {
            Path file = this.uploadPath.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + fileName, e);
        }
    
    @Override
    public StorageType getStorageType() {
        return StorageType.LOCAL;
    }
}
