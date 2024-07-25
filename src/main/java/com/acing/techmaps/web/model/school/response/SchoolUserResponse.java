package com.acing.techmaps.web.model.school.response;

import com.acing.techmaps.domain.entities.school.Role;
import com.acing.techmaps.domain.entities.school.SchoolUser;

import java.util.UUID;

public record SchoolUserResponse(UUID id, UUID schoolId, UUID userId, Role role) {

    public static SchoolUserResponse fromSchoolUser(SchoolUser schoolUser) {
        return new SchoolUserResponse(
                schoolUser.getId(), schoolUser.getSchoolId(),
                schoolUser.getUserId(), schoolUser.getRole()
        );
    }
}
