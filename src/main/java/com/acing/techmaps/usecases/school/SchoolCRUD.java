package com.acing.techmaps.usecases.school;

import com.acing.techmaps.domain.entities.school.School;
import com.acing.techmaps.web.model.school.request.SchoolRequest;

import java.util.UUID;

public interface SchoolCRUD {
    School create(SchoolRequest request);
    School getById(UUID id);
    School getByName(String name);
    School updateSchool(SchoolRequest request, UUID id);
}
