package com.acing.techmaps.web.model.school.request;

import com.acing.techmaps.domain.entities.school.School;

public record SchoolRequest(String name) {
    public SchoolRequest(String name) {
        this.name = name;
    }

    public School toSchool() {
        return School.fromRequest(name);
    }

}
