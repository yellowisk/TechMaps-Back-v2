package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.usecases.user.gateway.UserDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDAOImpl implements UserDAO {
    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    @Override
    public User add(User user) {
        UUID userId = Generators.timeBasedEpochGenerator().generate();

        jdbcTemplate.update(insertUserQuery, rs-> {
            rs.setObject(1, userId);
            rs.setString(2, user.getEmail());
            rs.setString(3, user.getUsername());
            rs.setString(4, user.getPassword());
        });

        return user.createWithId(userId);
    }

    @Override
    public Optional<User> findById(UUID id) {
        try {
            User user = jdbcTemplate.queryForObject(selectUserByIdQuery, this::mapperUserFromRs, id);

            return Optional.of(user);
        } catch (EmptyResultDataAccessException err) {
             throw new HttpException(HttpStatus.NOT_FOUND, "Could not find user with id: " + id);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(selectUserByEmailQuery,
                    this::mapperUserFromRs, email);

            if(Objects.isNull(user)) {
                throw new HttpException(HttpStatus.NOT_FOUND, "Could not find user with email: " + email);
            }

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            User user = jdbcTemplate.queryForObject(selectUserByUsernameQuery,
                    this::mapperUserFromRs, username);

            if(Objects.isNull(user)) {
                throw new HttpException(HttpStatus.NOT_FOUND, "Could not find user with username: " + username);
            }

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean userExists(UUID id) {
        Boolean exists = jdbcTemplate.queryForObject(existsUserIdQuery, Boolean.class, id);
        return Objects.nonNull(exists) && exists;
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update(updateUserQuery, user.getEmail(), user.getUsername(),
                user.getPassword(), user.getId());

        return user;
    }

    private User mapperUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");

        return User.createFull(id, email, username, password);
    }
}