package com.acing.techmaps.web.model.school.request;

import com.acing.techmaps.domain.entities.school.SchoolUser;

import java.util.UUID;

public record SchoolUserRequest(UUID schoolId, UUID userId, String role) {

    public SchoolUser toSchoolUser() {
        return SchoolUser.fromRequest(schoolId, userId, role);
    }
}
