package com.acing.techmaps.web.controller;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;
import com.acing.techmaps.usecases.dashboard.DashboardCRUD;
import com.acing.techmaps.web.model.dashboard.response.DashboardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DashboardController {
    private final DashboardCRUD dashboardCRUD;

    public DashboardController(DashboardCRUD dashboardCRUD) {
        this.dashboardCRUD = dashboardCRUD;
    }

    @PostMapping("/api/v1/dashboards/{userId}")
    public ResponseEntity<DashboardResponse> createDashboard(@PathVariable UUID userId) {
        Dashboard dashboard = dashboardCRUD.create(userId);
        return ResponseEntity.ok(DashboardResponse.createFromDashboard(dashboard));
    }

    @GetMapping("/api/v1/dashboards/{dashboardId}")
    public ResponseEntity<DashboardResponse> getDashboardById(@PathVariable UUID dashboardId) {
        Dashboard dashboard = dashboardCRUD.getById(dashboardId);
        return ResponseEntity.ok(DashboardResponse.createFromDashboard(dashboard));
    }

    @GetMapping("/api/v1/dashboards/users/{userId}")
    public ResponseEntity<DashboardResponse> getDashboardByUserId(@PathVariable UUID userId) {
        Dashboard dashboard = dashboardCRUD.getByUserId(userId);
        return ResponseEntity.ok(DashboardResponse.createFromDashboard(dashboard));
    }

}
