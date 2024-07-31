package com.acing.techmaps.domain.entities.roadmap;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class RoadmapUser {
    private UUID id;
    private UUID roadmapId;
    private UUID userId;
    private Boolean isDone;
    private Timestamp startTime;
    private Timestamp endTime;

    public RoadmapUser(UUID id, UUID roadmapId, UUID userId, Boolean isDone, Timestamp startTime, Timestamp endTime) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.userId = userId;
        this.isDone = isDone;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RoadmapUser(UUID roadmapId, UUID userId) {
        this.roadmapId = roadmapId;
        this.userId = userId;
    }

    public static RoadmapUser createFull(UUID id, UUID roadmapId, UUID userId, Boolean isDone, Timestamp startTime, Timestamp endTime) {
        return new RoadmapUser(id, roadmapId, userId, isDone, startTime, endTime);
    }

    public static RoadmapUser fromRequest(UUID roadmapId, UUID userId) {
        return new RoadmapUser(roadmapId, userId);
    }

}
