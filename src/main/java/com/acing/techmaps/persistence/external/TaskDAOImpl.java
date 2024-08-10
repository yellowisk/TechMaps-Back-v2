package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.task.Task;
import com.acing.techmaps.usecases.task.gateway.TaskDAO;
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
public class TaskDAOImpl implements TaskDAO {
    private final JdbcTemplate jdbcTemplate;

    public TaskDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.task-dao.insert.task}")
    private String insertTaskQuery;

    @Value("${queries.sql.task-dao.select.task-by-id}")
    private String selectTaskByIdQuery;

    @Value("${queries.sql.task-dao.select.task-by-stage-id}")
    private String selectTaskByStageIdQuery;

    @Value("${queries.sql.task-dao.update.task}")
    private String updateTaskQuery;

    @Value("${queries.sql.task-dao.delete.task}")
    private String deleteTaskQuery;

    @Override
    public Task add(Task task) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertTaskQuery, id, task.getStageId(), task.getTitle(), task.getDescription(), task.getPosition());
        return Task.createFull(id, task.getStageId(), task.getTitle(), task.getDescription(), task.getPosition());
    }

    @Override
    public Task findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectTaskByIdQuery, this::mapperTaskFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find Task with id " + id);
        }
    }

    @Override
    public List<Task> findByStage(UUID stageId) {
        try {
            return jdbcTemplate.query(selectTaskByStageIdQuery, this::mapperTaskFromRs, stageId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find Tasks with stage-id " + stageId);
        }
    }

    @Override
    public Task update(Task task) {
        jdbcTemplate.update(updateTaskQuery, task.getTitle(), task.getDescription(), task.getPosition(), task.getId());
        return task;
    }

    @Override
    public void delete(Task task) {
        jdbcTemplate.update(deleteTaskQuery, task.getId());
    }

    private Task mapperTaskFromRs(ResultSet rs, int rowNum) throws SQLException {
        return new Task(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("stage_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("position")
        );
    }
}
