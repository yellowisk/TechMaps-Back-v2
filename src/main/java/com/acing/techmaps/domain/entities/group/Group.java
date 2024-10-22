package com.acing.techmaps.domain.entities.group;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Group {
    private UUID id;
    private UUID parentId;
    private String name;

    public Group(UUID id, UUID parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Group(UUID parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }

    public static Group createFull(UUID id, UUID parentId, String name) {
        return new Group(id, parentId, name);
    }

    public static Group fromRequest(UUID parentId, String name) {
        return new Group(parentId, name);
    }
}
