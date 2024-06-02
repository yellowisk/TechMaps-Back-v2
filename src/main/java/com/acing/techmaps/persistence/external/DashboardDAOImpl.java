package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.dashboard.Dashboard;
import com.acing.techmaps.usecases.dashboard.gateway.DashboardDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class DashboardDAOImpl implements DashboardDAO {
    private JdbcTemplate jdbcTemplate;

    public DashboardDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.dashboard-dao.insert.dashboard}")
    private String insertDashboardQuery;
    @Value("${queries.sql.dashboard-dao.select.dashboard-by-id}")
    private String selectDashboardByIdQuery;
    @Value("${queries.sql.dashboard-dao.select.dashboard-by-user-id}")
    private String selectDashboardByUserIdQuery;
    @Value("${queries.sql.dashboard-dao.exists.dashboard-by-id}")
    private String existsDashboardIdQuery;

    @Override
    public Dashboard add(UUID userId) {
        UUID dashboardId = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertDashboardQuery, dashboardId, userId, 0);

        return Dashboard.createFull(dashboardId, userId, 0);
    }

    @Override
    public Optional<Dashboard> findById(UUID id) {
        try {
            Dashboard dashboard = jdbcTemplate.queryForObject(selectDashboardByIdQuery,
                    this::mapperDashboardFromRs, id);

            if (Objects.isNull(dashboard)) {
                throw new HttpException(HttpStatus.NOT_FOUND, "Could not find dashboard with id: " + id);
            }

            return Optional.of(dashboard);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Dashboard> findByUserId(UUID userId) {
        try {
            Dashboard dashboard = jdbcTemplate.queryForObject(selectDashboardByUserIdQuery,
                    this::mapperDashboardFromRs, userId);

            if (Objects.isNull(dashboard)) {
                throw new HttpException(HttpStatus.NOT_FOUND, "Could not find dashboard with user id: " + userId);
            }

            return Optional.of(dashboard);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean dashboardExists(UUID id) {
        return jdbcTemplate.queryForObject(existsDashboardIdQuery, Boolean.class, id);
    }

    private Dashboard mapperDashboardFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID userId = (UUID) rs.getObject("user_id");
        int totalRoadmaps = rs.getInt("total_roadmaps");

        return Dashboard.createFull(id, userId, totalRoadmaps);
    }
}
