package com.acing.techmaps.web.model.roadmap.request;

import com.acing.techmaps.domain.entities.roadmap.RoadmapUser;

import java.util.UUID;

public record RoadmapUserRequest(UUID roadmapId, UUID userId) {

    public RoadmapUser toRoadmapUser() {
        return RoadmapUser.fromRequest(roadmapId, userId);
    }

}
