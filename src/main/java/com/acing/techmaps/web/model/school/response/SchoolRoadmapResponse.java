package com.acing.techmaps.web.model.school.response;

import com.acing.techmaps.domain.entities.school.SchoolRoadmap;

import java.util.UUID;

public record SchoolRoadmapResponse(UUID id, UUID schoolId, UUID roadmapId) {

    public static SchoolRoadmapResponse fromSchoolRoadmap(SchoolRoadmap schoolRoadmap) {
        return new SchoolRoadmapResponse(
                schoolRoadmap.getId(),
                schoolRoadmap.getSchoolId(),
                schoolRoadmap.getRoadmapId()
        );
    }
}
