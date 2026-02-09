package com.acing.techmaps.infrastructure.filestorage;

import com.acing.techmaps.domain.entities.filestorage.StorageType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface StorageStrategy {
    boolean supports(String mimeType);
    boolean canHandle(String fileName);
    String upload(MultipartFile file);
    Resource download(String fileName);
    boolean delete(String fileName);
    StorageType getStorageType();
}
