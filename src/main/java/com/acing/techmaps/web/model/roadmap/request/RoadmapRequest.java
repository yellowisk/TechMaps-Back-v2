package com.acing.techmaps.web.model.roadmap.request;

import com.acing.techmaps.domain.entities.roadmap.Roadmap;

public record RoadmapRequest(String name, String language) {

    public Roadmap toRoadmap() {
        return Roadmap.fromRequest(name, language);
    }

}
