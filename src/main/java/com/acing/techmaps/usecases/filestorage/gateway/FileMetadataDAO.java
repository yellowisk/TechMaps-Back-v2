package com.acing.techmaps.usecases.filestorage.gateway;

import com.acing.techmaps.domain.entities.filestorage.FileMetadata;
import com.acing.techmaps.domain.entities.filestorage.StorageType;
import java.util.List;
import java.util.UUID;

public interface FileMetadataDAO {
    FileMetadata save(FileMetadata fileMetadata);
    FileMetadata findById(UUID id);
    void deleteById(UUID id);
    List<FileMetadata> findByOriginalFileName(String originalFileName);
    List<FileMetadata> findByCreatorId(UUID creatorId);
    List<FileMetadata> findByStorageType(StorageType storageType);
    List<FileMetadata> findAllOrderedByCreatedAt();
    FileMetadata findByPath(String path);
}
