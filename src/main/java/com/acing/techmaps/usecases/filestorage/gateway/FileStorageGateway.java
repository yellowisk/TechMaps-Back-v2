package com.acing.techmaps.usecases.filestorage.gateway;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileStorageGateway {
    String upload(MultipartFile file, UUID creatorId);
    Resource download(String fileName);
    void delete(UUID id);
}
