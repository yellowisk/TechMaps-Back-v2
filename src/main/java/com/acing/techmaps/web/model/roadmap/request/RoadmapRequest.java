package com.acing.techmaps.web.model.roadmap.request;

import com.acing.techmaps.domain.entities.roadmap.Roadmap;

import jakarta.validation.constraints.NotBlank;

public record RoadmapRequest(@NotBlank String name, @NotBlank String language) {

    public Roadmap toRoadmap() {
        return Roadmap.fromRequest(name, language);
    }

}
