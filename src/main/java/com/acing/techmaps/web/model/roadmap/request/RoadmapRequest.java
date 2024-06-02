package com.acing.techmaps.web.model.roadmap.request;

import com.acing.techmaps.domain.entities.roadmap.Roadmap;

public record RoadmapRequest(String name, String language) {
    public RoadmapRequest(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public Roadmap toRoadmap() {
        return Roadmap.fromRequest(name, language);
    }

}
