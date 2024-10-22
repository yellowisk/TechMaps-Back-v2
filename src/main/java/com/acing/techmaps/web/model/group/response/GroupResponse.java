package com.acing.techmaps.web.model.group.response;

import com.acing.techmaps.domain.entities.group.Group;

import java.util.UUID;

public record GroupResponse(UUID id, UUID parentId, String name) {

    public static GroupResponse fromGroup(Group group) {
        return new GroupResponse(
                group.getId(),
                group.getParentId(),
                group.getName()
        );
    }

}
