package com.acing.techmaps.web.model.roadmap.response;

import com.acing.techmaps.domain.entities.roadmap.Language;
import com.acing.techmaps.domain.entities.roadmap.Roadmap;

import java.util.UUID;

public record RoadmapResponse(UUID id, String name, Language language) {
    public RoadmapResponse(UUID id, String name, Language language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public static RoadmapResponse createFromRoadmap(Roadmap roadmap) {
        return new RoadmapResponse(
                roadmap.getId(),
                roadmap.getName(),
                roadmap.getLanguage()
        );
    }

}
