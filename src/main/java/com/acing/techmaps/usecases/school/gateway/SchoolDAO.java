package com.acing.techmaps.usecases.school.gateway;

import com.acing.techmaps.domain.entities.school.School;

import java.util.UUID;

public interface SchoolDAO {
    School add(School school);
    School findById(UUID id);
    School findByName(String name);
    School update(School school);
    Boolean schoolExists(UUID id);
}
