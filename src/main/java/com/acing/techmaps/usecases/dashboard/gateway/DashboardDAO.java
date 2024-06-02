package com.acing.techmaps.usecases.dashboard.gateway;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;

import java.util.*;

public interface DashboardDAO {
    Dashboard add(UUID id);
    Optional<Dashboard> findById(UUID id);
    Optional<Dashboard> findByUserId(UUID userId);
    Boolean dashboardExists(UUID id);
}
