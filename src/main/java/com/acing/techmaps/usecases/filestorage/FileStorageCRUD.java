package com.acing.techmaps.usecases.filestorage;

import com.acing.techmaps.domain.entities.filestorage.FileMetadata;
import com.acing.techmaps.domain.entities.filestorage.StorageType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FileStorageCRUD {
    String uploadFile(MultipartFile file, UUID creatorId);
    Resource downloadFile(String fileName);
    void deleteFile(UUID id);
    FileMetadata getFileById(UUID id);
    List<FileMetadata> getFilesByOriginalName(String originalName);
    List<FileMetadata> getFilesByCreatorId(UUID creatorId);
    List<FileMetadata> getFilesByStorageType(StorageType storageType);
    List<FileMetadata> getAllFiles();
}
