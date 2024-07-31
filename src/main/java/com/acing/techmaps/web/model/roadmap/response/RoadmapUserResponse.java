package com.acing.techmaps.web.model.roadmap.response;

import com.acing.techmaps.domain.entities.roadmap.RoadmapUser;

import java.sql.Timestamp;
import java.util.UUID;

public record RoadmapUserResponse(UUID id, UUID roadmapId, UUID userId, boolean isDone, Timestamp startTime, Timestamp endTime) {

    public static RoadmapUserResponse createFromRoadmapUser(RoadmapUser roadmapUser) {
        return new RoadmapUserResponse(
                roadmapUser.getId(),
                roadmapUser.getRoadmapId(),
                roadmapUser.getUserId(),
                roadmapUser.getIsDone(),
                roadmapUser.getStartTime(),
                roadmapUser.getEndTime()
        );
    }

}
