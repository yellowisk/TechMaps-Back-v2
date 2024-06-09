package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.school.School;
import com.acing.techmaps.usecases.school.gateway.SchoolDAO;
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
public class SchoolDAOImpl implements SchoolDAO {

    private JdbcTemplate jdbcTemplate;

    public SchoolDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.school-dao.insert.school}")
    private String insertSchoolQuery;

    @Value("${queries.sql.school-dao.select.school-by-id}")
    private String selectSchoolByIdQuery;

    @Value("${queries.sql.school-dao.select.school-by-name}")
    private String selectSchoolByNameQuery;

    @Value("${queries.sql.school-dao.update.school}")
    private String updateSchoolQuery;

    @Value("${queries.sql.school-dao.exists.school-by-id}")
    private String existsSchoolIdQuery;

    @Override
    public School add(School school) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertSchoolQuery, id, school.getName());
        return School.createFull(id, school.getName());
    }

    @Override
    public School findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectSchoolByIdQuery, this::mapperSchoolFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find school with id: " + id);
        }
    }

    @Override
    public School findByName(String name) {
        try {
            return jdbcTemplate.queryForObject(selectSchoolByNameQuery, this::mapperSchoolFromRs, name);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find school with name: " + name);
        }
    }

    @Override
    public School update(School school) {
        jdbcTemplate.update(updateSchoolQuery, school.getName(), school.getId());
        return School.createFull(school.getId(), school.getName());
    }

    @Override
    public Boolean schoolExists(UUID id) {
        return jdbcTemplate.queryForObject(existsSchoolIdQuery, Boolean.class, id);
    }

    private School mapperSchoolFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String name = rs.getString("name");
        return School.createFull(id, name);
    }

}
