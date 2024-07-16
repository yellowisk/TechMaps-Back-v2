package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.school.SchoolRoadmap;
import com.acing.techmaps.usecases.school.gateway.SchoolRoadmapDAO;
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
public class SchoolRoadmapDAOImpl implements SchoolRoadmapDAO {
    private final JdbcTemplate jdbcTemplate;

    public SchoolRoadmapDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.school-roadmap-dao.insert.school-roadmap}$")
    private String insertSchoolRoadmapQuery;

    @Value("${queries.sql.school-roadmap-dao.select.school-roadmap-by-id}$")
    private String selectSchoolRoadmapByIdQuery;

    @Value("${queries.sql.school-roadmap-dao.select.school-roadmap-by-school-id}$")
    private String selectSchoolRoadmapBySchoolIdQuery;

    @Value("${queries.sql.school-roadmap-dao.select.school-roadmap-by-roadmap-id}$")
    private String selectSchoolRoadmapByRoadmapIdQuery;

    @Value("${queries.sql.school-roadmap-dao.delete.school-roadmap}$")
    private String deleteSchoolRoadmapQuery;

    @Value("${queries.sql.school-roadmap-dao.exists.school-roadmap-by-id}$")
    private String existsSchoolRoadmapByIdQuery;

    @Override
    public SchoolRoadmap add(SchoolRoadmap schoolRoadmap) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertSchoolRoadmapQuery, id,
                schoolRoadmap.getSchoolId(), schoolRoadmap.getRoadmapId());
        return SchoolRoadmap.createFull(id, schoolRoadmap.getSchoolId(), schoolRoadmap.getRoadmapId());
    }

    @Override
    public SchoolRoadmap findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectSchoolRoadmapByIdQuery, this::mapperSchoolRoadmapFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find school-roadmap with id: " + id);
        }
    }

    @Override
    public List<SchoolRoadmap> findBySchool(UUID schoolId) {
        try {
            return jdbcTemplate.query(selectSchoolRoadmapBySchoolIdQuery, this::mapperSchoolRoadmapFromRs, schoolId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find school-roadmap with school id: " + schoolId);
        }
    }

    @Override
    public List<SchoolRoadmap> findByRoadmap(UUID roadmapId) {
        try {
            return jdbcTemplate.query(selectSchoolRoadmapByRoadmapIdQuery, this::mapperSchoolRoadmapFromRs, roadmapId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find school-roadmap with roadmap id: " + roadmapId);
        }
    }

    @Override
    public void delete(SchoolRoadmap schoolRoadmap) {
        jdbcTemplate.update(deleteSchoolRoadmapQuery, schoolRoadmap.getId());
    }

    @Override
    public Boolean schoolRoadmapExists(UUID id) {
        return jdbcTemplate.queryForObject(existsSchoolRoadmapByIdQuery, Boolean.class, id);
    }

    private SchoolRoadmap mapperSchoolRoadmapFromRs(ResultSet rs, int rowNum) throws SQLException {
        return SchoolRoadmap.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("school_id"),
                (UUID) rs.getObject("roadmap_id")
        );
    }
}
