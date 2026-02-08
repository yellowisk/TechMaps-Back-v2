package com.acing.techmaps.usecases.filestorage;

import com.acing.techmaps.usecases.filestorage.gateway.FileStorageGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileStorageCRUDImpl implements FileStorageCRUD {
    
    private final FileStorageGateway fileStorageGateway;

    @Autowired
    public FileStorageCRUDImpl(FileStorageGateway fileStorageGateway) {
        this.fileStorageGateway = fileStorageGateway;
    }

    @Override
    public String uploadFile(MultipartFile file, UUID creatorId) {
        return fileStorageGateway.upload(file, creatorId);
    }

    @Override
    public Resource downloadFile(String fileName) {
        return fileStorageGateway.download(fileName);
    }
}
