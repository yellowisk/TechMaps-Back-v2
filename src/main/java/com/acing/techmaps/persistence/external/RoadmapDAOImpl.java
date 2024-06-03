package com.acing.techmaps.persistence.external;


import com.acing.techmaps.domain.entities.roadmap.Language;
import com.acing.techmaps.domain.entities.roadmap.Roadmap;
import com.acing.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class RoadmapDAOImpl implements RoadmapDAO {
    private final JdbcTemplate jdbcTemplate;
    @Value("${queries.sql.roadmap-dao.insert.roadmap}")
    private String insertRoadmapQuery;
    @Value("${queries.sql.roadmap-dao.select.roadmap-by-id}")
    private String selectRoadmapByIdQuery;
    @Value("${queries.sql.roadmap-dao.select.roadmap-by-name}")
    private String selectRoadmapByNameQuery;
    @Value("${queries.sql.roadmap-dao.update.roadmap}")
    private String updateRoadmapQuery;
    @Value("${queries.sql.roadmap-dao.exists.roadmap-by-id}")
    private String existsRoadmapIdQuery;

    public RoadmapDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Roadmap add(Roadmap roadmap) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertRoadmapQuery, id, roadmap.getName(), roadmap.getLanguage().name());
        return Roadmap.createFull(id, roadmap.getName(), roadmap.getLanguage());
    }

    @Override
    public Roadmap findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectRoadmapByIdQuery, this::mapperRoadmapFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find roadmap with id: " + id);
        }
    }

    @Override
    public Roadmap findByName(String name) {
        try {
            return jdbcTemplate.queryForObject(selectRoadmapByNameQuery, this::mapperRoadmapFromRs, name);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find roadmap with name: " + name);
        }
    }

    @Override
    public Roadmap update(Roadmap roadmap) {
        jdbcTemplate.update(updateRoadmapQuery, roadmap.getName(), roadmap.getLanguage().name(), roadmap.getId());
        return Roadmap.createFull(roadmap.getId(), roadmap.getName(), roadmap.getLanguage());
    }

    @Override
    public Boolean roadmapExists(UUID id) {
        return jdbcTemplate.queryForObject(existsRoadmapIdQuery, Boolean.class, id);
    }

    public Roadmap mapperRoadmapFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String name = rs.getString("name");
        Language language = Language.valueOf(rs.getString("language"));
        return Roadmap.createFull(id, name, language);
    }
}
