package com.acing.techmaps.usecases.school;



import com.acing.techmaps.domain.entities.school.SchoolRoadmap;
import com.acing.techmaps.web.model.school.request.SchoolRoadmapRequest;

import java.util.List;
import java.util.UUID;

public interface SchoolRoadmapCRUD {
    SchoolRoadmap create (SchoolRoadmapRequest request);
    SchoolRoadmap getById(UUID id);
    List<SchoolRoadmap> getBySchoolId(UUID schoolId);
    List<SchoolRoadmap> getByRoadmapId(UUID roadmapId);
    void delete(UUID id);
}
