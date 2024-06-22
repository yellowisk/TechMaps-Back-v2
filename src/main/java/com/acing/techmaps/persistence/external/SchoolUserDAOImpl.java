package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.school.Role;
import com.acing.techmaps.domain.entities.school.SchoolUser;
import com.acing.techmaps.usecases.school.gateway.SchoolUserDAO;
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
public class SchoolUserDAOImpl implements SchoolUserDAO {
    private final JdbcTemplate jdbcTemplate;

    public SchoolUserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.school-user-dao.insert.school-user}")
    private String insertSchoolUserQuery;

    @Value("${queries.sql.school-user-dao.select.school-user-by-id}")
    private String selectSchoolUserByIdQuery;

    @Value("${queries.sql.school-user-dao.select.school-user-by-school-id}")
    private String selectSchoolUserBySchoolIdQuery;

    @Value("${queries.sql.school-user-dao.select.school-user-by-user-id}")
    private String selectSchoolUserByUserIdQuery;

    @Value("${queries.sql.school-user-dao.update.role}")
    private String updateRoleQuery;

    @Value("${queries.sql.school-user-dao.delete.school-user}")
    private String deleteSchoolUserQuery;

    @Value("${queries.sql.school-user-dao.exists.school-user-by-id}")
    private String existsSchoolUserIdQuery;

    @Override
    public SchoolUser add(SchoolUser schoolUser) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertSchoolUserQuery, id, schoolUser.getSchoolId(),
                schoolUser.getUserId(), schoolUser.getRole().name());
        return SchoolUser.createFull(id, schoolUser.getSchoolId(), schoolUser.getUserId(), schoolUser.getRole());
    }

    @Override
    public SchoolUser findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectSchoolUserByIdQuery, this::mapperSchoolUserFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find school-user with id: " + id);
        }
    }

    @Override
    public List<SchoolUser> findBySchool(UUID schoolId) {
        try {
            return jdbcTemplate.query(selectSchoolUserBySchoolIdQuery, this::mapperSchoolUserFromRs, schoolId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find school-user with school id: " + schoolId);
        }
    }

    @Override
    public List<SchoolUser> findByUser(UUID userId) {
        try {
            return jdbcTemplate.query(selectSchoolUserByUserIdQuery, this::mapperSchoolUserFromRs, userId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find school-user with user id: " + userId);
        }
    }

    @Override
    public SchoolUser updateRole(SchoolUser schoolUser) {
        jdbcTemplate.update(updateRoleQuery, schoolUser.getRole().name(), schoolUser.getId());
        return schoolUser;
    }

    @Override
    public void delete(SchoolUser schoolUser) {
        jdbcTemplate.update(deleteSchoolUserQuery, schoolUser.getId());
    }

    @Override
    public Boolean schoolUserExists(UUID id) {
        return jdbcTemplate.queryForObject(existsSchoolUserIdQuery, Boolean.class, id);
    }

    private SchoolUser mapperSchoolUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        return SchoolUser.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("school_id"),
                (UUID) rs.getObject("user_id"),
                Role.valueOf(rs.getString("role"))
        );
    }
}
