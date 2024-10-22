package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.group.GroupUser;
import com.acing.techmaps.domain.entities.group.Role;
import com.acing.techmaps.usecases.group.gateway.GroupUserDAO;
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
public class GroupUserDAOImpl implements GroupUserDAO {
    private final JdbcTemplate jdbcTemplate;

    public GroupUserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.group-user-dao.insert.group-user}")
    private String insertGroupUserQuery;

    @Value("${queries.sql.group-user-dao.select.group-user-by-id}")
    private String selectGroupUserByIdQuery;

    @Value("${queries.sql.group-user-dao.select.group-user-by-group-id}")
    private String selectGroupUserByGroupIdQuery;

    @Value("${queries.sql.group-user-dao.select.group-user-by-user-id}")
    private String selectGroupUserByUserIdQuery;

    @Value("${queries.sql.group-user-dao.update.role}")
    private String updateRoleQuery;

    @Value("${queries.sql.group-user-dao.delete.group-user}")
    private String deleteGroupUserQuery;

    @Value("${queries.sql.group-user-dao.exists.group-user-by-id}")
    private String existsGroupUserIdQuery;

    @Override
    public GroupUser add(GroupUser groupUser) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertGroupUserQuery, id, groupUser.getGroupId(),
                groupUser.getUserId(), groupUser.getRole().name());
        return GroupUser.createFull(id, groupUser.getGroupId(), groupUser.getUserId(),
                groupUser.getRole());
    }

    @Override
    public GroupUser findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectGroupUserByIdQuery, this::mapperGroupUserFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-user with id: " + id);
        }
    }

    @Override
    public List<GroupUser> findByGroup(UUID groupId) {
        try {
            return jdbcTemplate.query(selectGroupUserByGroupIdQuery, this::mapperGroupUserFromRs, groupId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-users with group id: " + groupId);
        }
    }

    @Override
    public List<GroupUser> findByUser(UUID userId) {
        try {
            return jdbcTemplate.query(selectGroupUserByUserIdQuery, this::mapperGroupUserFromRs, userId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-users with user id: " + userId);
        }
    }

    @Override
    public GroupUser updateRole(GroupUser groupUser) {
        jdbcTemplate.update(updateRoleQuery, groupUser.getRole().name(), groupUser.getId());
        return groupUser;
    }

    @Override
    public void delete(GroupUser groupUser) {
        jdbcTemplate.update(deleteGroupUserQuery, groupUser.getId());
    }

    @Override
    public Boolean groupUserExists(UUID id) {
        return jdbcTemplate.queryForObject(existsGroupUserIdQuery, Boolean.class, id);
    }

    private GroupUser mapperGroupUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        return GroupUser.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("group_id"),
                (UUID) rs.getObject("user_id"),
                Role.valueOf(rs.getString("role"))
        );
    }
}
