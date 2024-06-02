package com.acing.techmaps.usecases.dashboard;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;
import com.acing.techmaps.usecases.dashboard.gateway.DashboardDAO;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DashboardCRUDImpl implements DashboardCRUD{
    private DashboardDAO dashboardDAO;

    public DashboardCRUDImpl(DashboardDAO dashboardDAO) {
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public Dashboard create(UUID userId) {
        return dashboardDAO.add(userId);
    }

    @Override
    public Dashboard getById(UUID id) {
        return dashboardDAO.findById(id).get();
    }

    @Override
    public Dashboard getByUserId(UUID userId) {
        return dashboardDAO.findByUserId(userId).get();
    }
}
