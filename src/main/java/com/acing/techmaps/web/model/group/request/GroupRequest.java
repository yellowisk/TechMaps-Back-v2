package com.acing.techmaps.web.model.group.request;

import com.acing.techmaps.domain.entities.group.Group;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record GroupRequest(UUID parentId, UUID creatorId,
                           @NotBlank String name) {

    public Group toGroup() {
        return Group.fromRequest(parentId, creatorId, name);
    }

}
