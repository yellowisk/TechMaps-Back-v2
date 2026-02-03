package com.acing.techmaps.web.model.dashboard.request;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DashboardRequest(
        @NotNull UUID userId,
        @NotNull @Min(0) Integer totalRoadmaps
) {

    public Dashboard toDashboard() {
        return Dashboard.fromRequest(userId, totalRoadmaps);
    }
}
