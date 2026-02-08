package com.acing.techmaps.usecases.filestorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageCRUD {
    String uploadFile(MultipartFile file);
    Resource downloadFile(String fileName);
}
