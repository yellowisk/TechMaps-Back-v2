package com.acing.techmaps.web.model.school.request;

import com.acing.techmaps.domain.entities.school.School;

public record SchoolRequest(String name) {

    public School toSchool() {
        return School.fromRequest(name);
    }

}
