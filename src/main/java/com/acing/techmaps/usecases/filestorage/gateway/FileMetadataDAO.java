package com.acing.techmaps.usecases.filestorage.gateway;

import com.acing.techmaps.domain.entities.filestorage.FileMetadata;
import java.util.UUID;

public interface FileMetadataDAO {
    FileMetadata save(FileMetadata fileMetadata);
    FileMetadata findById(UUID id);
    FileMetadata findByPath(String path);
}
