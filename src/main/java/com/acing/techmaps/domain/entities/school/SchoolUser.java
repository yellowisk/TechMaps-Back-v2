package com.acing.techmaps.domain.entities.school;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SchoolUser {
    private UUID id;
    private UUID schoolId;
    private UUID userId;
    private Role role;

    public SchoolUser(UUID id, UUID schoolId, UUID userId, Role role) {
        this.id = id;
        this.schoolId = schoolId;
        this.userId = userId;
        this.role = role;
    }

    public SchoolUser(UUID schoolId, UUID userId, Role role) {
        this.schoolId = schoolId;
        this.userId = userId;
        this.role = role;
    }

    public static SchoolUser createFull(UUID id, UUID schoolId, UUID userId, Role role) {
        return new SchoolUser(id, schoolId, userId, role);
    }

    public static SchoolUser fromRequest(UUID schoolId, UUID userId, String role) {
        return new SchoolUser(schoolId, userId, Role.valueOf(role));
    }
}
