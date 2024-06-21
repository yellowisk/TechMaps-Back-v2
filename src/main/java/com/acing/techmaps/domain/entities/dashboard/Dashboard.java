package com.acing.techmaps.domain.entities.dashboard;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
public class Dashboard {
    private UUID id;
    private UUID userId;
    private int totalRoadmaps;

    public Dashboard(UUID id, UUID userId, int totalRoadmaps) {
        this.id = id;
        this.userId = userId;
        this.totalRoadmaps = totalRoadmaps;
    }

    public Dashboard(UUID userId, int totalRoadmaps) {
        this.userId = userId;
        this.totalRoadmaps = totalRoadmaps;
    }

    public Dashboard(UUID id) {
        this.id = id;
    }

    public static Dashboard createFull(UUID id, UUID userId, int totalRoadmaps) {
        return new Dashboard(id, userId, totalRoadmaps);
    }

    public static Dashboard fromRequest(UUID userId, int totalRoadmaps) {
        return new Dashboard(userId, totalRoadmaps);
    }

    public static Dashboard createWithOnlyId(UUID id) {
        return new Dashboard(id);
    }

}
