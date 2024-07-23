package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.task.TaskUser;
import com.acing.techmaps.usecases.task.gateway.TaskUserDAO;
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
public class TaskUserDAOImpl implements TaskUserDAO {
    private final JdbcTemplate jdbcTemplate;

    public TaskUserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.task-user-dao.insert.task-user}")
    private String insertTaskUserQuery;

    @Value("${queries.sql.task-user-dao.select.task-user-by-id}")
    private String selectTaskUserByIdQuery;

    @Value("${queries.sql.task-user-dao.select.task-user-by-task-id}")
    private String selectTaskUserByTaskIdQuery;

    @Value("${queries.sql.task-user-dao.select.task-user-by-roadmap-user-id}")
    private String selectTaskUserByRoadmapUserIdQuery;

    @Value("${queries.sql.task-user-dao.update.task-user-is-done}")
    private String updateTaskUserQuery;

    @Value("${queries.sql.task-user-dao.delete.task-user}")
    private String deleteTaskUserQuery;

    @Override
    public TaskUser add(TaskUser taskUser) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertTaskUserQuery, id, taskUser.getTaskId(), taskUser.getRoadmapUserId(), taskUser.getIsDone());
        return TaskUser.createFull(id, taskUser.getTaskId(), taskUser.getRoadmapUserId(), taskUser.getIsDone());
    }

    @Override
    public TaskUser findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectTaskUserByIdQuery, this::mapperTaskUserFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find task-user with id: " + id);
        }
    }

    @Override
    public TaskUser findByTaskId(UUID taskId) {
        try {
            return jdbcTemplate.queryForObject(selectTaskUserByTaskIdQuery, this::mapperTaskUserFromRs, taskId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find task-users with taskId: " + taskId);
        }
    }

    @Override
    public List<TaskUser> findByRoadmapUserId(UUID roadmapUserId) {
        try {
            return jdbcTemplate.query(selectTaskUserByRoadmapUserIdQuery, this::mapperTaskUserFromRs, roadmapUserId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find task-users with roadmapUserId: " + roadmapUserId);
        }
    }

    @Override
    public TaskUser update(TaskUser taskUser) {
        jdbcTemplate.update(updateTaskUserQuery, taskUser.getIsDone(), taskUser.getId());
        return taskUser;
    }

    @Override
    public void delete(TaskUser taskUser) {
        jdbcTemplate.update(deleteTaskUserQuery, taskUser.getId());
    }

    private TaskUser mapperTaskUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        return TaskUser.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("task_id"),
                (UUID) rs.getObject("roadmap_user_id"),
                rs.getBoolean("is_done")
        );
    }
}
