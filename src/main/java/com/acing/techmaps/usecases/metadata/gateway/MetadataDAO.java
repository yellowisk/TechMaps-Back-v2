package com.acing.techmaps.usecases.metadata.gateway;

import com.acing.techmaps.domain.entities.metadata.Metadata;

import java.sql.Timestamp;
import java.util.UUID;

public interface MetadataDAO {
    Metadata save(Metadata metadata);
    Metadata findById(UUID id);
    Metadata findByOriginalName(String originalName);
    Metadata findByStoredName(String storedName);
    Metadata findByMimeType(String mimeType);
    Metadata findByCreatedAt(Timestamp createdAt);
    void deleteById(UUID id);
}
