package com.acing.techmaps.infrastructure.filestorage;

import com.acing.techmaps.usecases.filestorage.gateway.FileStorageGateway;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageGateway {

    private final List<StorageStrategy> strategies;
    private final Tika tika = new Tika();

    @Autowired
    public FileStorageServiceImpl(List<StorageStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public String upload(MultipartFile file) {
        try {
            String detectedType = tika.detect(file.getInputStream());
            return strategies.stream()
                    .filter(strategy -> strategy.supports(detectedType))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Unsupported file type detected: " + detectedType))
                    .upload(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to detect file type", e);
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
