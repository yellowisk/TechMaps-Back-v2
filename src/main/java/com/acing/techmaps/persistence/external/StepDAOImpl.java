package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.step.Step;
import com.acing.techmaps.usecases.step.gateway.StepDAO;
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
public class StepDAOImpl implements StepDAO {
    private final JdbcTemplate jdbcTemplate;

    public StepDAOImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Value("${queries.sql.step-dao.insert.step}$")
    private String insertStepQuery;

    @Value("${queries.sql.step-dao.select.step-by-id}")
    private String selectStepByIdQuery;

    @Value("${queries.sql.step-dao.select.step-by-task-id}$")
    private String selectStepByTaskIdQuery;

    @Value("${queries.sql.step-dao.update.step}$")
    private String updateStepQuery;

    @Value("${queries.sql.step-dao.delete.step}$")
    private String deleteStepQuery;

    @Override
    public Step add(Step step) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertStepQuery, id, step.getTaskId(), step.getPosition(), step.getText(), step.getLink());
        return Step.createFull(id, step.getTaskId(), step.getPosition(), step.getText(), step.getLink());
    }

    @Override
    public Step findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectStepByIdQuery, this::mapperStepFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find step with id: " + id);
        }
    }

    @Override
    public List<Step> findByTask(UUID taskId) {
        try {
            return jdbcTemplate.query(selectStepByTaskIdQuery, this::mapperStepFromRs, taskId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find steps with task id: " + taskId);
        }
    }

    @Override
    public Step update(Step step) {
        jdbcTemplate.update(updateStepQuery, step.getPosition(), step.getText(), step.getLink(), step.getId());
        return Step.createFull(step.getId(), step.getTaskId(), step.getPosition(), step.getText(), step.getLink());
    }

    @Override
    public void delete(Step step) {
        jdbcTemplate.update(deleteStepQuery, step.getId());
    }

    private Step mapperStepFromRs(ResultSet rs, int rowNum) throws SQLException {
        return Step.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("task_id"),
                rs.getInt("position"),
                rs.getString("text"),
                rs.getString("link")
        );
    }
}
