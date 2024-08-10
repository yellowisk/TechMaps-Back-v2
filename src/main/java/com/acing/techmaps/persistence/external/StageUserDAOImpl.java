package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.usecases.stage.gateway.StageUserDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
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
public class StageUserDAOImpl implements StageUserDAO {
    private final JdbcTemplate jdbcTemplate;

    public StageUserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.stage-user-dao.insert.stage-user}")
    private String insertStageUserQuery;

    @Value("${queries.sql.stage-user-dao.select.stage-user-by-id}")
    private String selectStageUserByIdQuery;

    @Value("${queries.sql.stage-user-dao.select.stage-user-by-stage-id}")
    private String selectStageUserByStageIdQuery;

    @Value("${queries.sql.stage-user-dao.select.stage-user-by-roadmap-user-id}")
    private String selectStageUserByRoadmapUserIdQuery;

    @Value("${queries.sql.stage-user-dao.update.stage-user}")
    private String updateStageUserQuery;

    @Value("${queries.sql.stage-user-dao.delete.stage-user}")
    private String deleteStageUserQuery;

    @Override
    public StageUser add(StageUser stageUser) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertStageUserQuery, id, stageUser.getStageId(), stageUser.getRoadmapUserId(), stageUser.isDone(), stageUser.getPosition());
        return StageUser.createFull(id, stageUser.getStageId(), stageUser.getRoadmapUserId(), stageUser.isDone(), stageUser.getPosition());
    }

    @Override
    public StageUser findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectStageUserByIdQuery, this::mapperStageUserFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find stage-user with id " + id);
        }
    }

    @Override
    public StageUser findByStageId(UUID stageId) {
        try {
            return jdbcTemplate.queryForObject(selectStageUserByStageIdQuery, this::mapperStageUserFromRs, stageId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find stage-user with stage id " + stageId);
        }
    }

    @Override
    public List<StageUser> findByRoadmapUserId(UUID roadmapUserId) {
        try {
            return jdbcTemplate.query(selectStageUserByRoadmapUserIdQuery, this::mapperStageUserFromRs, roadmapUserId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find stage-users with roadmap-user id " + roadmapUserId);
        }
    }

    @Override
    public StageUser update(StageUser stageUser) {
        jdbcTemplate.update(updateStageUserQuery, stageUser.isDone(), stageUser.getPosition(), stageUser.getId());
        return stageUser;
    }

    @Override
    public void delete(StageUser stageUser) {
        jdbcTemplate.update(deleteStageUserQuery, stageUser.getId());
    }

    private  StageUser mapperStageUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        return StageUser.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("stage_id"),
                (UUID) rs.getObject("roadmap_user_id"),
                rs.getBoolean("is_done"),
                rs.getInt("position")
        );
    }

}
