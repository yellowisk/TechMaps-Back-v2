package com.acing.techmaps.domain.entities.dashboard;

import java.sql.Timestamp;
import java.util.UUID;

public class Dashboard {
    private UUID id;
    private UUID userId;
    private int totalRoadmaps;

    public Dashboard(UUID id, UUID userId, int totalRoadmaps) {
        this.id = id;
        this.userId = userId;
        this.totalRoadmaps = totalRoadmaps;
    }

    public Dashboard(UUID userId, int totalRoadmaps, Timestamp userSince) {
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

    public Dashboard() {
    }

    public static Dashboard createFull(UUID id, UUID userId, int totalRoadmaps) {
        return new Dashboard(id, userId, totalRoadmaps);
    }

    public static Dashboard createFromDashboard(UUID userId, int totalRoadmaps) {
        return new Dashboard(userId, totalRoadmaps);
    }

    public static Dashboard createWithOnlyId(UUID id) {
        return new Dashboard(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getTotalRoadmaps() {
        return totalRoadmaps;
    }

    public void setTotalRoadmaps(int totalRoadmaps) {
        this.totalRoadmaps = totalRoadmaps;
    }
}
