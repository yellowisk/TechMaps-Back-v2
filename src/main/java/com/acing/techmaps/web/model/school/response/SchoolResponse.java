package com.acing.techmaps.web.model.school.response;

import com.acing.techmaps.domain.entities.school.School;

import java.util.UUID;

public record SchoolResponse(UUID id, String name) {

    public SchoolResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SchoolResponse fromSchool(School school) {
        return new SchoolResponse(
                school.getId(),
                school.getName()
        );
    }

}
