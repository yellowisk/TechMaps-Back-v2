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

    @Value("${queries.sql.task-dao.select.task-by-task}")
    private String selectTaskByTaskQuery;

    @Value("${queries.sql.task-dao.update.name}")
    private String updateTaskNameQuery;

    @Value("${queries.sql.task-dao.delete.task}")
    private String deleteTaskQuery;

    @Value("${queries.sql.task-dao.exists.task-by-id}")
    private String taskExistsQuery;

    @Override
    public Task add(Task task) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertTaskQuery, id, task.getTask(), task.getName());
        return Task.createFull(id, task.getTask(), task.getName());
    }

    @Override
    public Task findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectTaskByIdQuery, this::mapperTaskFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find task with id: " + id);
        }
    }

    @Override
    public List<Task> findByTask(UUID task) {
        try {
            return jdbcTemplate.query(selectTaskByTaskQuery, this::mapperTaskFromRs, task);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find tasks with taskId: " + task);
        }
    }

    @Override
    public Task update(Task task) {
        jdbcTemplate.update(updateTaskNameQuery, task.getName(), task.getId());
        return Task.createFull(task.getId(), task.getTask(), task.getName());
    }

    @Override
    public void delete(Task task) {
        jdbcTemplate.update(deleteTaskQuery, task.getId());
    }

    @Override
    public Boolean taskExists(UUID id) {
        return jdbcTemplate.queryForObject(taskExistsQuery, Boolean.class, id);
    }

    private Task mapperTaskFromRs(ResultSet rs, int rowNum) throws SQLException {
        return Task.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("task"),
                rs.getString("name")
        );
    }
}
