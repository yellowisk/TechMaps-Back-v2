package com.acing.techmaps.web.model.roadmap.request;

import com.acing.techmaps.domain.entities.roadmap.RoadmapUser;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RoadmapUserRequest(@NotNull UUID roadmapId, @NotNull UUID userId) {

    public RoadmapUser toRoadmapUser() {
        return RoadmapUser.fromRequest(roadmapId, userId);
    }

}
