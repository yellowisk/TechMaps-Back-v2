package com.acing.techmaps.web.model.group.request;

import com.acing.techmaps.domain.entities.group.GroupRoadmap;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GroupRoadmapRequest(@NotNull UUID groupId,
                                  @NotNull UUID roadmapId) {

    public GroupRoadmap toGroupRoadmap() {
        return GroupRoadmap.fromRequest(groupId, roadmapId);
    }
}
