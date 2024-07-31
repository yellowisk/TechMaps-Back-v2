package com.acing.techmaps.web.model.dashboard.response;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;

import java.util.UUID;

public record DashboardResponse(UUID id, UUID userId, int totalRoadmaps) {

    public static DashboardResponse createFromDashboard(Dashboard dashboard) {
        return new DashboardResponse(
                dashboard.getId(),
                dashboard.getUserId(),
                dashboard.getTotalRoadmaps()
        );
    }
}
