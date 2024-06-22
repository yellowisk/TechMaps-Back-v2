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
import java.util.UUID;

@Repository
public class DashboardDAOImpl implements DashboardDAO {
    private final JdbcTemplate jdbcTemplate;
    @Value("${queries.sql.dashboard-dao.insert.dashboard}")
    private String insertDashboardQuery;
    @Value("${queries.sql.dashboard-dao.select.dashboard-by-id}")
    private String selectDashboardByIdQuery;
    @Value("${queries.sql.dashboard-dao.select.dashboard-by-user-id}")
    private String selectDashboardByUserIdQuery;
    @Value("${queries.sql.dashboard-dao.exists.dashboard-by-id}")
    private String existsDashboardIdQuery;
    public DashboardDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dashboard add(UUID userId) {
        UUID dashboardId = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertDashboardQuery, dashboardId, userId, 0);

        return Dashboard.createFull(dashboardId, userId, 0);
    }

    @Override
    public Dashboard findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectDashboardByIdQuery, this::mapperDashboardFromRs, id);
        } catch (EmptyResultDataAccessException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find dashboard with id: " + id);
        }
    }

    @Override
    public Dashboard findByUserId(UUID userId) {
        try {
            return jdbcTemplate.queryForObject(selectDashboardByUserIdQuery, this::mapperDashboardFromRs, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find dashboard with user id: " + userId);
        }
    }

    @Override
    public Boolean dashboardExists(UUID id) {
        return jdbcTemplate.queryForObject(existsDashboardIdQuery, Boolean.class, id);
    }

    private Dashboard mapperDashboardFromRs(ResultSet rs, int rowNum) throws SQLException {
        return Dashboard.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("user_id"),
                rs.getInt("total_roadmaps")
        );
    }
}
