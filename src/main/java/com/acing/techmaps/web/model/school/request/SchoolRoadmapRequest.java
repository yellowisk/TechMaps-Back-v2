package com.acing.techmaps.web.model.school.request;

import com.acing.techmaps.domain.entities.school.SchoolRoadmap;

import java.util.UUID;

public record SchoolRoadmapRequest(UUID schoolId, UUID roadmapId) {
    public SchoolRoadmapRequest(UUID schoolId, UUID roadmapId) {
        this.schoolId = schoolId;
        this.roadmapId = roadmapId;
    }

    public SchoolRoadmap toSchoolRoadmap() {
        return SchoolRoadmap.fromRequest(schoolId, roadmapId);
    }
}
