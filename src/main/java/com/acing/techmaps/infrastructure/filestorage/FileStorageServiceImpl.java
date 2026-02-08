package com.acing.techmaps.infrastructure.filestorage;

import com.acing.techmaps.domain.entities.filestorage.FileMetadata;
import com.acing.techmaps.usecases.filestorage.gateway.FileMetadataDAO;
import com.acing.techmaps.usecases.filestorage.gateway.FileStorageGateway;
import com.fasterxml.uuid.Generators;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;
import java.io.File;

@Service
public class FileStorageServiceImpl implements FileStorageGateway {

    private final List<StorageStrategy> strategies;
    private final FileMetadataDAO fileMetadataDAO;
    private final Tika tika = new Tika();

    @Autowired
    public FileStorageServiceImpl(List<StorageStrategy> strategies, FileMetadataDAO fileMetadataDAO) {
        this.strategies = strategies;
        this.fileMetadataDAO = fileMetadataDAO;
    }

    @Override
    public String upload(MultipartFile file, UUID creatorId) {
        try {
            String detectedType = tika.detect(file.getInputStream());
            StorageStrategy strategy = strategies.stream()
                    .filter(s -> s.supports(detectedType))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Unsupported file type detected: " + detectedType));

            String path = strategy.upload(file);

            FileMetadata metadata = new FileMetadata(
                    Generators.timeBasedGenerator().generate(),
                    new File(path).getName(),
                    file.getOriginalFilename(),
                    detectedType,
                    file.getSize(),
                    strategy.getStorageType(),
                    path,
                    Timestamp.from(java.time.Instant.now()),
                    creatorId
            );

            fileMetadataDAO.save(metadata);

            return path;
        } catch (IOException e) {
            throw new RuntimeException("Failed to process file upload", e);
        }
    }

    @Override
    public Resource download(String fileName) {
       return strategies.stream()
               .filter(strategy -> strategy.canHandle(fileName))
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("Unknown file source for: " + fileName))
               .download(fileName);
    }
}
