package com.acing.techmaps.domain.entities.filestorage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMetadata {
    private UUID id;
    private UUID creatorId;
    private String fileName;
    private String originalFileName;
    private String contentType;
    private Long size;
    private StorageType storageType;
    private String path;
    private Timestamp createdAt;
}
