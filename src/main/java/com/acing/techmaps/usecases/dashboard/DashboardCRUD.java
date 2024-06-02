package com.acing.techmaps.usecases.dashboard;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;
import java.util.UUID;

public interface DashboardCRUD {
    Dashboard create(UUID userId);
    Dashboard getById(UUID id);
    Dashboard getByUserId(UUID userId);
}
