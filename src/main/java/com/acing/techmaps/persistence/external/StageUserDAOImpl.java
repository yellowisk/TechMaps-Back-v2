package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.stage.StageUser;
<<<<<<< HEAD
import com.acing.techmaps.usecases.stage_user.gateway.Stage_UserDAO;
=======
import com.acing.techmaps.usecases.stage.gateway.StageUserDAO;
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
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
<<<<<<< HEAD
public class StageUserDAOImpl implements Stage_UserDAO {
=======
public class StageUserDAOImpl implements StageUserDAO {
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
    private final JdbcTemplate jdbcTemplate;

    public StageUserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.stage-user-dao.insert.stage-user}")
    private String insertStageUserQuery;

    @Value("${queries.sql.stage-user-dao.select.stage-user-by-id}")
    private String selectStageUserByIdQuery ;

<<<<<<< HEAD
    @Value("${queries.sql.stage-user-dao.select.stage-user-by-user-id}")
    private String selectStageUserByStageUserIdQuery;

    @Value("${queries.sql.stage-user-dao.select.stage-user-by-stage-id")
    private String selectStageUserByUserIdQuery;

    @Value("${queries.sql.stage-user-dao.update.stage-user-is-Completed}")
    private String updateStageUserCompletedQuery;
=======
    @Value("${queries.sql.stage-user-dao.select.stage-user-by-stage-user-id}")
    private String selectStageUserByStageUserIdQuery;

    @Value("${queries.sql.stage-user-dao.select.stage-user-by-user-id")
    private String selectStageUserByUserIdQuery;

    @Value("${queries.sql.stage-user-dao.update.stage-user-is-Completed}")
    private String updateStageUserisCompletedQuery;
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc

    @Value("${queries.sql.stage-user-dao.delete.stage-user}")
    private String deleteStageUserQuery;

    @Override
    public StageUser add(StageUser stageUser) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
<<<<<<< HEAD
        jdbcTemplate.update(insertStageUserQuery, id, stageUser.getUserId(), stageUser.getStageId(),stageUser.getIsCompleted());
        return StageUser.createFull(id, stageUser.getStageId(),stageUser.getStageId(),stageUser.getIsCompleted());
=======
        jdbcTemplate.update(insertStageUserQuery, id, stageUser.getUserId(), stageUser.getStageId(),stageUser.isCompleted());

        return StageUser.createFull(id, stageUser.getStageId(), stageUser.getStageId(), stageUser.isCompleted());
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
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
<<<<<<< HEAD
    public StageUser findByUserId(UUID userId) {
        try {
            return jdbcTemplate.queryForObject(selectStageUserByUserIdQuery, this::mapperStageUserFromRs, userId);
=======
    public List<StageUser> findByStageId(UUID userId) {
        try {
            return jdbcTemplate.query(selectStageUserByUserIdQuery, this::mapperStageUserFromRs, userId);
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find stage-user with id: " + userId);
        }
    }

    @Override
<<<<<<< HEAD
    public List<StageUser> findByStageId(UUID stageId) {
        try {
            return jdbcTemplate.query( selectStageUserByStageUserIdQuery, this::mapperStageUserFromRs, stageId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find task-users with StageId: " + stageId);
=======
    public List<StageUser> findByUserId(UUID stageId) {
        try {
            return jdbcTemplate.query(selectStageUserByStageUserIdQuery, this::mapperStageUserFromRs, stageId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find task-users with roadmapUserId: " + stageId);
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
        }
    }

    @Override
    public StageUser update(StageUser stageUser) {
<<<<<<< HEAD
        jdbcTemplate.update(updateStageUserCompletedQuery, stageUser.getIsCompleted(), stageUser.getId());
=======
        jdbcTemplate.update(updateStageUserisCompletedQuery, stageUser.isCompleted(), stageUser.getId());
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
        return stageUser;
    }

    @Override
<<<<<<< HEAD
    public void delete(StageUser stage_user) {
        jdbcTemplate.update(deleteStageUserQuery, stage_user.getId());
=======
    public void delete(StageUser stageUser) {
        jdbcTemplate.update(deleteStageUserQuery, stageUser.getId());
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
    }

    private StageUser mapperStageUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        return StageUser.createFull(
                (UUID) rs.getObject("id"),
<<<<<<< HEAD
                (UUID) rs.getObject("user_id"),
                (UUID) rs.getObject("stage_id"),
=======
                (UUID) rs.getObject("stage_id"),
                (UUID) rs.getObject("user_id"),
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
                rs.getBoolean("is_Completed")
        );
    }
}

