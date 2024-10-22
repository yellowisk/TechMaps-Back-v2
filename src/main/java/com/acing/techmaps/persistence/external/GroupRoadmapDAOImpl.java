package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.group.GroupRoadmap;
import com.acing.techmaps.usecases.group.gateway.GroupRoadmapDAO;
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
public class GroupRoadmapDAOImpl implements GroupRoadmapDAO {
    private final JdbcTemplate jdbcTemplate;

    public GroupRoadmapDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.group-roadmap-dao.insert.group-roadmap}$")
    private String insertGroupRoadmapQuery;

    @Value("${queries.sql.group-roadmap-dao.select.group-roadmap-by-id}$")
    private String selectGroupRoadmapByIdQuery;

    @Value("${queries.sql.group-roadmap-dao.select.group-roadmap-by-group-id}$")
    private String selectGroupRoadmapByGroupIdQuery;

    @Value("${queries.sql.group-roadmap-dao.select.group-roadmap-by-roadmap-id}$")
    private String selectGroupRoadmapByRoadmapIdQuery;

    @Value("${queries.sql.group-roadmap-dao.delete.group-roadmap}$")
    private String deleteGroupRoadmapQuery;

    @Value("${queries.sql.group-roadmap-dao.exists.group-roadmap-by-id}$")
    private String existsGroupRoadmapByIdQuery;

    @Override
    public GroupRoadmap add(GroupRoadmap groupRoadmap) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertGroupRoadmapQuery, id,
                groupRoadmap.getGroupId(), groupRoadmap.getRoadmapId());
        return GroupRoadmap.createFull(id, groupRoadmap.getGroupId(), groupRoadmap.getRoadmapId());
    }

    @Override
    public GroupRoadmap findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectGroupRoadmapByIdQuery, this::mapperGroupRoadmapFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-roadmap with id: " + id);
        }
    }

    @Override
    public List<GroupRoadmap> findByGroupId(UUID groupId) {
        try {
            return jdbcTemplate.query(selectGroupRoadmapByGroupIdQuery, this::mapperGroupRoadmapFromRs, groupId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-roadmaps with group id: " + groupId);
        }
    }

    @Override
    public List<GroupRoadmap> findByRoadmapId(UUID roadmapId) {
        try {
            return jdbcTemplate.query(selectGroupRoadmapByRoadmapIdQuery, this::mapperGroupRoadmapFromRs, roadmapId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-roadmaps with roadmap id: " + roadmapId);
        }
    }

    @Override
    public void delete(GroupRoadmap groupRoadmap) {
        jdbcTemplate.update(deleteGroupRoadmapQuery, groupRoadmap.getId());
    }

    @Override
    public Boolean groupRoadmapExists(UUID id) {
        return jdbcTemplate.queryForObject(existsGroupRoadmapByIdQuery, Boolean.class, id);
    }

    private GroupRoadmap mapperGroupRoadmapFromRs(ResultSet rs, int rowNum) throws SQLException {
        return GroupRoadmap.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("group_id"),
                (UUID) rs.getObject("roadmap_id")
        );
    }
}
