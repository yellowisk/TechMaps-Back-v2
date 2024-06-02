package com.acing.techmaps.domain.entities.roadmap;

import java.sql.Timestamp;
import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(UUID roadmapId) {
        this.roadmapId = roadmapId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
