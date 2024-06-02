package com.acing.techmaps.usecases.roadmap.gateway;

import com.acing.techmaps.domain.entities.roadmap.Roadmap;

import java.util.Optional;
import java.util.UUID;

public interface RoadmapDAO {
    Roadmap add(Roadmap roadmap);

    Optional<Roadmap> findById(UUID id);

    Optional<Roadmap> findByName(String name);

    Roadmap update(Roadmap roadmap);

    Boolean roadmapExists(UUID id);
}
