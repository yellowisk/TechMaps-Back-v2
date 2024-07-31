package com.acing.techmaps.usecases.roadmap.gateway;

import com.acing.techmaps.domain.entities.roadmap.RoadmapUser;

import java.util.List;
import java.util.UUID;

public interface RoadmapUserDAO {
    RoadmapUser add(RoadmapUser roadmapUser);
    RoadmapUser findById(UUID id);
    List<RoadmapUser> findByUserId(UUID userId);
    List<RoadmapUser> findByRoadmapId(UUID roadmapId);
    RoadmapUser updateEndTime(RoadmapUser roadmapUser);
    RoadmapUser updateIsDone(RoadmapUser roadmapUser);
    void delete(UUID id);
}
