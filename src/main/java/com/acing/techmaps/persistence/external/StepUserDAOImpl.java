package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.step.StepUser;
import com.acing.techmaps.usecases.step.gateway.StepUserDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class StepUserDAOImpl implements StepUserDAO {
    private final JdbcTemplate jdbcTemplate;
    public StepUserDAOImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    private StepUser mapperFromRs(ResultSet rs, int rowNum) throws SQLException {
        return new StepUser(
                UUID.fromString(rs.getString("id")),
                UUID.fromString(rs.getString("step_id")),
                UUID.fromString(rs.getString("roadmap_user_id")),
                rs.getBoolean("is_done")
        );
    }

    @Value("${queries.sql.step-user-dao.insert}")
    private String insertQuery;

    @Value("${queries.sql.step-user-dao.select.by-id}")
    private String selectByIdQuery;

    @Value("${queries.sql.step-user-dao.select.by-roadmap-user-id}")
    private String selectByRoadmapUserIdQuery;

    @Value("${queries.sql.step-user-dao.select.by-step-id}")
    private String selectByStepIdQuery;

    @Value("${queries.sql.step-user-dao.update.status}")
    private String updateStatusQuery;

    @Value("${queries.sql.step-user-dao.delete}")
    private String deleteQuery;

    @Override
    public StepUser add(StepUser stepUser) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertQuery, id, stepUser.getStepId(), stepUser.getRoadmapUserId(), stepUser.isDone());
        return new StepUser(id, stepUser.getStepId(), stepUser.getRoadmapUserId(), stepUser.isDone());
    }

    @Override
    public StepUser findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectByIdQuery, this::mapperFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find step-user with id " + id);
        }
    }

    @Override
    public List<StepUser> findByRoadmapUserId(UUID roadmapUserId) {
        try {
            return jdbcTemplate.query(selectByRoadmapUserIdQuery, this::mapperFromRs, roadmapUserId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find step-user with user id " + roadmapUserId);
        }
    }

    @Override
    public List<StepUser> findByStepId(UUID stepId) {
        try {
            return jdbcTemplate.query(selectByStepIdQuery, this::mapperFromRs, stepId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find step-user with step id " + stepId);
        }
    }

    @Override
    public StepUser updateStatus(StepUser stepUser) {
        jdbcTemplate.update(updateStatusQuery, stepUser.isDone(), stepUser.getId());
        return stepUser;
    }

    @Override
    public void delete(UUID id) {
        jdbcTemplate.update(deleteQuery, id);
    }
}

