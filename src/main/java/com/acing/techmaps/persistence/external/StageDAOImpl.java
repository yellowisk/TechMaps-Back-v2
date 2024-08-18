package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.school.Role;
import com.acing.techmaps.domain.entities.stage.Stage;
import com.acing.techmaps.usecases.stage.gateway.StageDAO;
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
public class StageDAOImpl implements StageDAO {
    private final JdbcTemplate jdbcTemplate;

    public StageDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.stage-dao.insert.stage}")
    private String insertStageQuery;

    @Value("${queries.sql.stage-dao.select.stage-by-id}")
    private String selectStageByIdQuery;

    @Value("${queries.sql.stage-dao.select.stage-by-roadmap-id}")
    private String selectStageByRoadmapQuery;

    @Value("${queries.sql.stage-dao.update.name}")
    private String updateStageNameQuery;

    @Value("${queries.sql.stage-dao.delete.stage}")
    private String deleteStageQuery;

    @Value("${queries.sql.stage-dao.exists.stage-by-id}")
    private String stageExistsQuery;

    @Override
    public Stage add(Stage stage) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertStageQuery, id, stage.getRoadmapId(), stage.getName(), stage.getPosition());
        return Stage.createFull(id, stage.getRoadmapId(), stage.getName(), stage.getPosition());
    }

    @Override
    public Stage findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectStageByIdQuery, this::mapperStageFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find stage with id: " + id);
        }
    }

    @Override
    public List<Stage> findByRoadmap(UUID roadmapId) {
        try {
            return jdbcTemplate.query(selectStageByRoadmapQuery, this::mapperStageFromRs, roadmapId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find stages with roadmapId: " + roadmapId);
        }
    }

    @Override
    public Stage update(Stage stage) {
        jdbcTemplate.update(updateStageNameQuery, stage.getName(), stage.getPosition(), stage.getId());
        return Stage.createFull(stage.getId(), stage.getRoadmapId(), stage.getName(), stage.getPosition());
    }

    @Override
    public void delete(Stage stage) {
        jdbcTemplate.update(deleteStageQuery, stage.getId());
    }

    @Override
    public Boolean stageExists(UUID id) {
        return jdbcTemplate.queryForObject(stageExistsQuery, Boolean.class, id);
    }

    private Stage mapperStageFromRs(ResultSet rs, int rowNum) throws SQLException {
        return Stage.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("roadmap_id"),
                rs.getString("name"),
                rs.getInt("position")
        );
    }

}
