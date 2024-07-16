package com.acing.techmaps.web.model.school.response;

import com.acing.techmaps.domain.entities.school.SchoolRoadmap;

import java.util.UUID;

public record SchoolRoadmapResponse(UUID id, UUID schoolId, UUID roadmapId) {
    public SchoolRoadmapResponse(UUID id, UUID schoolId, UUID roadmapId) {
        this.id = id;
        this.schoolId = schoolId;
        this.roadmapId = roadmapId;
    }

    public static SchoolRoadmapResponse fromSchoolRoadmap(SchoolRoadmap schoolRoadmap) {
        return new SchoolRoadmapResponse(
                schoolRoadmap.getId(),
                schoolRoadmap.getSchoolId(),
                schoolRoadmap.getRoadmapId()
        );
    }
}
