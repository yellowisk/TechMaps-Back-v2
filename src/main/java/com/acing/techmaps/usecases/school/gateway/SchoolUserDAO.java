package com.acing.techmaps.usecases.school.gateway;

import com.acing.techmaps.domain.entities.school.SchoolUser;

import java.util.List;
import java.util.UUID;

public interface SchoolUserDAO {
    SchoolUser add(SchoolUser schoolUser);
    SchoolUser findById(UUID id);
    List<SchoolUser> findBySchool(UUID schoolId);
    List<SchoolUser> findByUser(UUID  userId);
    SchoolUser updateRole(SchoolUser schoolUser);
    void delete(SchoolUser schoolUser);
    Boolean schoolUserExists(UUID id);
}
