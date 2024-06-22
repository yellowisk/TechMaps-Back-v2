package com.acing.techmaps.usecases.school;


import com.acing.techmaps.domain.entities.school.SchoolUser;
import com.acing.techmaps.web.model.school.request.SchoolUserRequest;

import java.util.List;
import java.util.UUID;

public interface SchoolUserCRUD {
    SchoolUser create (SchoolUserRequest request);
    SchoolUser getById(UUID id);
    List<SchoolUser> getBySchoolId(UUID schoolId);
    List<SchoolUser> getByUserId(UUID userId);
    SchoolUser updateRole(UUID id, String role);
    void delete(UUID id);
}
