package com.acing.techmaps.web.model.group.response;

import com.acing.techmaps.domain.entities.group.GroupRoadmap;

import java.util.UUID;

public record GroupRoadmapResponse(UUID id, UUID groupId, UUID roadmapId) {

    public static GroupRoadmapResponse fromGroupRoadmap(GroupRoadmap groupRoadmap) {
        return new GroupRoadmapResponse(
                groupRoadmap.getId(),
                groupRoadmap.getGroupId(),
                groupRoadmap.getRoadmapId()
        );
    }
}
