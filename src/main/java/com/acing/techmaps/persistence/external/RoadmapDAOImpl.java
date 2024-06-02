package com.acing.techmaps.persistence.external;


import com.acing.techmaps.domain.entities.roadmap.Language;
import com.acing.techmaps.domain.entities.roadmap.Roadmap;
import com.acing.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import com.acing.techmaps.web.exception.ResourceNotFoundException;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RoadmapDAOImpl implements RoadmapDAO {
    private final JdbcTemplate jdbcTemplate;

    public RoadmapDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    @Override
    public Roadmap add(Roadmap roadmap) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertRoadmapQuery, id, roadmap.getName(), roadmap.getLanguage().name());
        return Roadmap.createFull(id, roadmap.getName(), roadmap.getLanguage());
    }

    @Override
    public Optional<Roadmap> findById(UUID id) {
        try {
            Roadmap roadmap = jdbcTemplate.queryForObject(selectRoadmapByIdQuery, this::mapperRoadmapFromRs, id);

            return Optional.of(roadmap);
        } catch (EmptyResultDataAccessException err) {
            throw new ResourceNotFoundException("Could not find roadmap with id: " + id);
        }
    }

    @Override
    public Optional<Roadmap> findByName(String name) {
        try {
            Roadmap roadmap = jdbcTemplate.queryForObject(selectRoadmapByNameQuery, this::mapperRoadmapFromRs, name);

            return Optional.of(roadmap);
        } catch (EmptyResultDataAccessException err) {
            throw new ResourceNotFoundException("Could not find roadmap with name: " + name);
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
