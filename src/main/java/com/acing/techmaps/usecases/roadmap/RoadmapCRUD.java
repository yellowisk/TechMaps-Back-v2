package com.acing.techmaps.usecases.roadmap;

import com.acing.techmaps.domain.entities.roadmap.Roadmap;
import com.acing.techmaps.web.model.roadmap.request.RoadmapRequest;

import java.util.UUID;

public interface RoadmapCRUD {
    Roadmap create(RoadmapRequest request);

    Roadmap getById(UUID id);

    Roadmap getByName(String name);

    Roadmap updateRoadmap(RoadmapRequest request, UUID id);
}
