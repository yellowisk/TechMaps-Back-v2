package com.acing.techmaps.web.model.dashboard.response;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;

import java.util.UUID;

public class DashboardResponse {
    private UUID id;
    private UUID userId;
    private int totalRoadmaps;

    public DashboardResponse(UUID id, UUID userId, int totalRoadmaps) {
        this.id = id;
        this.userId = userId;
        this.totalRoadmaps = totalRoadmaps;
    }

    public static DashboardResponse createFromDashboard(Dashboard dashboard) {
        return new DashboardResponse(
                dashboard.getId(),
                dashboard.getUserId(),
                dashboard.getTotalRoadmaps()
        );
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
