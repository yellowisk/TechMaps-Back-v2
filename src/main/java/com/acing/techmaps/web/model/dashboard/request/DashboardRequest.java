package com.acing.techmaps.web.model.dashboard.request;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;

import java.sql.Timestamp;
import java.util.UUID;

public record DashboardRequest(UUID userId, int totalRoadmaps) {

    public Dashboard toDashboard() {
        return Dashboard.fromRequest(userId, totalRoadmaps);
    }
}
