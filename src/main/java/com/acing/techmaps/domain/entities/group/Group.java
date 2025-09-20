package com.acing.techmaps.domain.entities.group;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Group {
    private UUID id;
    private UUID parentId;
    private UUID creatorId;
    private String name;

    public Group(UUID id, UUID parentId, UUID creatorId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.creatorId = creatorId;
        this.name = name;
    }

    public Group(UUID parentId, UUID creatorId, String name) {
        this.parentId = parentId;
        this.creatorId = creatorId;
        this.name = name;
    }

    public static Group createFull(UUID id, UUID parentId, UUID creatorId, String name) {
        return new Group(id, parentId, creatorId, name);
    }

    public static Group fromRequest(UUID parentId, UUID creatorId, String name) {
        return new Group(parentId, creatorId, name);
    }
}
