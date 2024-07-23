package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.usecases.user.gateway.UserDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

@Repository
public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate;
    @Value("${queries.sql.user-dao.insert.user}")
    private String insertUserQuery;
    @Value("${queries.sql.user-dao.select.user-by-id}")
    private String selectUserByIdQuery;
    @Value("${queries.sql.user-dao.select.user-by-email}")
    private String selectUserByEmailQuery;
    @Value("${queries.sql.user-dao.select.user-by-username}")
    private String selectUserByUsernameQuery;
    @Value("${queries.sql.user-dao.update.user}")
    private String updateUserQuery;
    @Value("${queries.sql.user-dao.exists.user-by-id}")
    private String existsUserIdQuery;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User add(User user) {
        UUID userId = Generators.timeBasedEpochGenerator().generate();

        try {
            jdbcTemplate.update(insertUserQuery, rs -> {
                rs.setObject(1, userId);
                rs.setString(2, user.getEmail());
                rs.setString(3, user.getUsername());
                rs.setString(4, user.getPassword());
            });
        } catch (DuplicateKeyException err) {
            String duplicatedEntity = err.getMessage().contains("user_email_key") ? "email" : "username";
            throw new HttpException(HttpStatus.CONFLICT, "Specified " + duplicatedEntity + " is taken", duplicatedEntity + "-duplicated");
        }

        return user.createWithId(userId);
    }

    @Override
    public User findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectUserByIdQuery, this::mapperUserFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find user with id: " + id);
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(selectUserByEmailQuery, this::mapperUserFromRs, email);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find user with email: " + email);
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(selectUserByUsernameQuery, this::mapperUserFromRs, username);
        } catch (EmptyResultDataAccessException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find user with username: " + username);
        }
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update(updateUserQuery, user.getEmail(), user.getUsername(), user.getPassword(), user.getId());
        return user;
    }

    @Override
    public Boolean userExists(UUID id) {
        Boolean exists = jdbcTemplate.queryForObject(existsUserIdQuery, Boolean.class, id);
        return Objects.nonNull(exists) && exists;
    }

    private User mapperUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        return User.createFull(
                (UUID) rs.getObject("id"),
                rs.getString("email"),
                rs.getString("username"),
                rs.getString("password")
        );
    }
}
