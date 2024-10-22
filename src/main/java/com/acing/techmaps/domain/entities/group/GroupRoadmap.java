package com.acing.techmaps.domain.entities.group;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class GroupRoadmap {
    private UUID id;
    private UUID groupId;
    private UUID roadmapId;

    public GroupRoadmap(UUID id, UUID groupId, UUID roadmapId) {
        this.id = id;
        this.groupId = groupId;
        this.roadmapId = roadmapId;
    }

    public GroupRoadmap(UUID groupId, UUID roadmapId) {
        this.groupId = groupId;
        this.roadmapId = roadmapId;
    }

    public static GroupRoadmap createFull(UUID id, UUID groupId, UUID roadmapId) {
        return new GroupRoadmap(id, groupId, roadmapId);
    }

    public static GroupRoadmap fromRequest(UUID groupId, UUID roadmapId) {
        return new GroupRoadmap(groupId, roadmapId);
    }

}
