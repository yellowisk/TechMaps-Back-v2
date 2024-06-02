package com.acing.techmaps.domain.entities.school;

import com.acing.techmaps.domain.entities.school.Role;

import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(UUID schoolId) {
        this.schoolId = schoolId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
