package com.acing.techmaps.usecases.school;

import com.acing.techmaps.domain.entities.school.School;
import com.acing.techmaps.usecases.school.gateway.SchoolDAO;
import com.acing.techmaps.web.model.school.request.SchoolRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchoolCRUDImpl implements SchoolCRUD {
    private SchoolDAO schoolDAO;

    public SchoolCRUDImpl(SchoolDAO schoolDAO) {
        this.schoolDAO = schoolDAO;
    }

    @Override
    public School create(SchoolRequest request) {
        return schoolDAO.add(request.toSchool());
    }

    @Override
    public School getById(UUID id) {
        return schoolDAO.findById(id);
    }

    @Override
    public School getByName(String name) {
        return schoolDAO.findByName(name);
    }

    @Override
    public School updateSchool(SchoolRequest request, UUID id) {
        School school = request.toSchool();
        school.setId(id);
        return schoolDAO.update(school);
    }
}
