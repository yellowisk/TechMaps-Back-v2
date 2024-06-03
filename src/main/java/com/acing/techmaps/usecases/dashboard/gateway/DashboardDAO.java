package com.acing.techmaps.usecases.dashboard.gateway;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;

import java.util.Optional;
import java.util.UUID;

public interface DashboardDAO {
    Dashboard add(UUID id);

    Dashboard findById(UUID id);

    Dashboard findByUserId(UUID userId);

    Boolean dashboardExists(UUID id);
}
