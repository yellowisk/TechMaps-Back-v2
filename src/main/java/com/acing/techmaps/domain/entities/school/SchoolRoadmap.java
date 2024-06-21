package com.acing.techmaps.domain.entities.school;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class SchoolRoadmap {
    private UUID id;
    private UUID schoolId;
    private UUID roadmapId;

    public SchoolRoadmap(UUID id, UUID schoolId, UUID roadmapId) {
        this.id = id;
        this.schoolId = schoolId;
        this.roadmapId = roadmapId;
    }

    public SchoolRoadmap(UUID schoolId, UUID roadmapId) {
        this.schoolId = schoolId;
        this.roadmapId = roadmapId;
    }

    public static SchoolRoadmap createFull(UUID id, UUID schoolId, UUID roadmapId) {
        return new SchoolRoadmap(id, schoolId, roadmapId);
    }

    public static SchoolRoadmap fromRequest(UUID schoolId, UUID roadmapId) {
        return new SchoolRoadmap(schoolId, roadmapId);
    }

}
