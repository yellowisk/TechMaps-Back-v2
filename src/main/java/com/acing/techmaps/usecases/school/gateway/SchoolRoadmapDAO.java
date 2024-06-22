package com.acing.techmaps.usecases.school.gateway;

import com.acing.techmaps.domain.entities.school.SchoolRoadmap;

import java.util.List;
import java.util.UUID;

public interface SchoolRoadmapDAO {
    SchoolRoadmap add(SchoolRoadmap schoolRoadmap);
    SchoolRoadmap findById(UUID id);
    List<SchoolRoadmap> findBySchool(UUID schoolId);
    List<SchoolRoadmap> findByRoadmap(UUID roadmapId);
    void delete(SchoolRoadmap schoolRoadmap);
    Boolean schoolRoadmapExists(UUID id);
}
