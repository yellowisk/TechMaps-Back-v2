package com.acing.techmaps.domain.entities.metadata;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class Metadata {
    private UUID id;
    private UUID ownerId;
    private String originalName;
    private String storedName;
    private String mimeType;
    private long size;
    private Timestamp createdAt;

    public Metadata(UUID id, UUID ownerId, String originalName, String storedName, String mimeType, long size, Timestamp createdAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.originalName = originalName;
        this.storedName = storedName;
        this.mimeType = mimeType;
        this.size = size;
        this.createdAt = createdAt;
    }

    public Metadata(UUID ownerId, String originalName, String storedName, String mimeType, long size, Timestamp createdAt) {
        this.ownerId = ownerId;
        this.originalName = originalName;
        this.storedName = storedName;
        this.mimeType = mimeType;
        this.size = size;
        this.createdAt = createdAt;
    }
}
