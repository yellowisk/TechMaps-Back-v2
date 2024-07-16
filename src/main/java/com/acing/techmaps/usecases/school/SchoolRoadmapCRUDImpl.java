package com.acing.techmaps.usecases.school;

import com.acing.techmaps.domain.entities.school.SchoolRoadmap;
import com.acing.techmaps.usecases.school.gateway.SchoolRoadmapDAO;
import com.acing.techmaps.web.model.school.request.SchoolRoadmapRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SchoolRoadmapCRUDImpl implements SchoolRoadmapCRUD {
    private final SchoolRoadmapDAO schoolRoadmapDAO;

    public SchoolRoadmapCRUDImpl(SchoolRoadmapDAO schoolRoadmapDAO) {
        this.schoolRoadmapDAO = schoolRoadmapDAO;
    }

    @Override
    public SchoolRoadmap create(SchoolRoadmapRequest request) {
        return schoolRoadmapDAO.add(request.toSchoolRoadmap());
    }

    @Override
    public SchoolRoadmap getById(UUID id) {
        return schoolRoadmapDAO.findById(id);
    }

    @Override
    public List<SchoolRoadmap> getBySchoolId(UUID schoolId) {
        return schoolRoadmapDAO.findBySchool(schoolId);
    }

    @Override
    public List<SchoolRoadmap> getByRoadmapId(UUID roadmapId) {
        return schoolRoadmapDAO.findByRoadmap(roadmapId);
    }

    @Override
    public void delete(UUID id) {
        SchoolRoadmap schoolRoadmap = schoolRoadmapDAO.findById(id);
        schoolRoadmapDAO.delete(schoolRoadmap);
    }
}
