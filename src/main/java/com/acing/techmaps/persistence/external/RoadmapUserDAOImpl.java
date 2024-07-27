package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.roadmap.RoadmapUser;
import com.acing.techmaps.usecases.roadmap.gateway.RoadmapUserDAO;
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
public class RoadmapUserDAOImpl implements RoadmapUserDAO {
    private final JdbcTemplate jdbcTemplate;

    public RoadmapUserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.roadmap-user-dao.insert.roadmap-user}")
    private String insertRoadmapUserQuery;

    @Value("${queries.sql.roadmap-user-dao.select.roadmap-user-by-id}")
    private String selectRoadmapUserByIdQuery;

    @Value("${queries.sql.roadmap-user-dao.select.roadmap-user-by-user-id}")
    private String selectRoadmapUserByUserIdQuery;

    @Value("${queries.sql.roadmap-user-dao.select.roadmap-user-by-roadmap-id}")
    private String selectRoadmapUserByRoadmapIdQuery;

    @Value("${queries.sql.roadmap-user-dao.update.roadmap-user-end-time}")
    private String updateRoadmapUserEndTimeQuery;

    @Value("${queries.sql.roadmap-user-dao.update.roadmap-user-is-done}")
    private String updateRoadmapUserIsDoneQuery;

    @Value("${queries.sql.roadmap-user-dao.delete.roadmap-user}")
    private String deleteRoadmapUserQuery;

    @Override
    public RoadmapUser add(RoadmapUser roadmapUser) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertRoadmapUserQuery, id, roadmapUser.getUserId(), roadmapUser.getRoadmapId(), roadmapUser.getIsDone(), roadmapUser.getStartTime());
        return RoadmapUser.createFull(id, roadmapUser.getUserId(), roadmapUser.getRoadmapId(),
                roadmapUser.getIsDone(), roadmapUser.getStartTime(), roadmapUser.getEndTime());
    }

    @Override
    public RoadmapUser findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectRoadmapUserByIdQuery, this::mapperRoadmapUserFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find roadmap-user with id: " + id);
        }
    }

    @Override
    public List<RoadmapUser> findByUserId(UUID userId) {
        try {
            return jdbcTemplate.query(selectRoadmapUserByUserIdQuery, this::mapperRoadmapUserFromRs, userId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find roadmap-users with userId: " + userId);
        }

    }

    @Override
    public List<RoadmapUser> findByRoadmapId(UUID roadmapId) {
        try {
            return jdbcTemplate.query(selectRoadmapUserByRoadmapIdQuery, this::mapperRoadmapUserFromRs, roadmapId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find roadmap-users with roadmapId: " + roadmapId);
        }
    }

    @Override
    public RoadmapUser updateEndTime(RoadmapUser roadmapUser) {
        jdbcTemplate.update(updateRoadmapUserEndTimeQuery, roadmapUser.getEndTime(), roadmapUser.getId());
        return roadmapUser;
    }

    @Override
    public RoadmapUser updateIsDone(RoadmapUser roadmapUser) {
        jdbcTemplate.update(updateRoadmapUserIsDoneQuery, roadmapUser.getIsDone(), roadmapUser.getId());
        return roadmapUser;
    }

    @Override
    public void delete(UUID id) {
        jdbcTemplate.update(deleteRoadmapUserQuery, id);
    }

    private RoadmapUser mapperRoadmapUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        return RoadmapUser.createFull(
                UUID.fromString(rs.getString("id")),
                UUID.fromString(rs.getString("user_id")),
                UUID.fromString(rs.getString("roadmap_id")),
                rs.getBoolean("is_done"),
                rs.getTimestamp("start_time"),
                rs.getTimestamp("end_time")
        );
    }

}
