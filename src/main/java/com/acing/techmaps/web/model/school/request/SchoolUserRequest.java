package com.acing.techmaps.web.model.school.request;

import com.acing.techmaps.domain.entities.school.SchoolUser;

import java.util.UUID;

public record SchoolUserRequest(UUID schoolId, UUID userId, String role) {
    public SchoolUserRequest(UUID schoolId, UUID userId, String role) {
        this.schoolId = schoolId;
        this.userId = userId;
        this.role = role;
    }

    public SchoolUser toSchoolUser() {
        return SchoolUser.fromRequest(schoolId, userId, role);
    }
}
