package com.acing.techmaps.usecases.school;

import com.acing.techmaps.domain.entities.school.Role;
import com.acing.techmaps.domain.entities.school.SchoolUser;
import com.acing.techmaps.usecases.school.gateway.SchoolUserDAO;
import com.acing.techmaps.web.model.school.request.SchoolUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SchoolUserCRUDImpl implements SchoolUserCRUD  {
    private final SchoolUserDAO schoolUserDAO;

    public SchoolUserCRUDImpl(SchoolUserDAO schoolUserDAO) {
        this.schoolUserDAO = schoolUserDAO;
    }

    @Override
    public SchoolUser create(SchoolUserRequest request) {
        return schoolUserDAO.add(request.toSchoolUser());
    }

    @Override
    public SchoolUser getById(UUID id) {
        return schoolUserDAO.findById(id);
    }

    @Override
    public List<SchoolUser> getBySchoolId(UUID schoolId) {
        return schoolUserDAO.findBySchool(schoolId);
    }

    @Override
    public List<SchoolUser> getByUserId(UUID userId) {
        return schoolUserDAO.findByUser(userId);
    }

    @Override
    public SchoolUser updateRole(UUID id, String role) {
        SchoolUser schoolUser = schoolUserDAO.findById(id);
        schoolUser.setRole(Role.valueOf(role));
        return schoolUserDAO.updateRole(schoolUser);
    }

    @Override
    public void delete(UUID id) {
        SchoolUser schoolUser = schoolUserDAO.findById(id);
        schoolUserDAO.delete(schoolUser);
    }
}
