package com.acing.techmaps.web.model.group.request;

import com.acing.techmaps.domain.entities.group.GroupRoadmap;

import java.util.UUID;

public record GroupRoadmapRequest(UUID groupId, UUID roadmapId) {

    public GroupRoadmap toGroupRoadmap() {
        return GroupRoadmap.fromRequest(groupId, roadmapId);
    }
}
