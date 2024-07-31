package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.usecases.stage_user.gateway.Stage_UserDAO;
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
public class StageUserDAOImpl implements Stage_UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public StageUserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.stage-user-dao.insert.stage-user}")
    private String insertStageUserQuery;

    @Value("${queries.sql.stage-user-dao.select.stage-user-by-id}")
    private String selectStageUserByIdQuery ;

    @Value("${queries.sql.stage-user-dao.select.stage-user-by-stage-user-id}")
    private String selectStageUserByStageUserIdQuery;

    @Value("${queries.sql.stage-user-dao.select.stage-user-by-user-id")
    private String selectStageUserByUserIdQuery;

    @Value("${queries.sql.stage-user-dao.update.stage-user-is-Completed}")
    private String updateStageUserisCompletedQuery;

    @Value("${queries.sql.stage-user-dao.delete.stage-user}")
    private String deleteStageUserQuery;

    @Override
    public StageUser add(StageUser stage_user) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertStageUserQuery, id, stage_user.getUserId(), stage_user.getStageId(),stage_user.isCompleted());
        return StageUser.createFull(id, stage_user.getStageId(),stage_user.getStageId(),stage_user.isCompleted());
    }

    @Override
    public StageUser findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectStageUserByIdQuery, this::mapperStageUserFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find stage-user with id: " + id);
        }
    }

    @Override
    public StageUser findByStageId(UUID userId) {
        try {
            return jdbcTemplate.queryForObject(selectStageUserByUserIdQuery, this::mapperStageUserFromRs, userId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find stage-user with id: " + userId);
        }
    }

    @Override
    public List<StageUser> findByUserId(UUID stageId) {
        try {
            return jdbcTemplate.query( selectStageUserByStageUserIdQuery, this::mapperStageUserFromRs, stageId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find task-users with roadmapUserId: " + stageId);
        }
    }

    @Override
    public StageUser update(StageUser stage_user) {
        jdbcTemplate.update(updateStageUserisCompletedQuery, stage_user.isCompleted(), stage_user.getId());
        return stage_user;
    }

    @Override
    public void delete(StageUser stage_user) {
        jdbcTemplate.update(deleteStageUserQuery, stage_user.getId());
    }

    private StageUser mapperStageUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        return StageUser.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("stage_id"),
                (UUID) rs.getObject("user_id"),
                rs.getBoolean("is_Completed")
        );
    }
}

