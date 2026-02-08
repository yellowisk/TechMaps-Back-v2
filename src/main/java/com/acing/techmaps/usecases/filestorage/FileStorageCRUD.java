package com.acing.techmaps.usecases.filestorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileStorageCRUD {
    String uploadFile(MultipartFile file, UUID creatorId);
    Resource downloadFile(String fileName);
}
