package com.acing.techmaps.usecases.filestorage.gateway;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageGateway {
    String upload(MultipartFile file);
    Resource download(String fileName);
}
