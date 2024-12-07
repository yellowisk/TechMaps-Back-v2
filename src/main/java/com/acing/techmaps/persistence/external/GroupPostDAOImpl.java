package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.group.GroupPost;
import com.acing.techmaps.usecases.group.gateway.GroupPostDAO;
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
public class GroupPostDAOImpl implements GroupPostDAO {

    private final JdbcTemplate jdbcTemplate;

    public GroupPostDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.group-post-dao.insert.group-post}$")
    private String insertGroupPostQuery;

    @Value("${queries.sql.group-post-dao.select.group-post-by-id}$")
    private String selectGroupPostByIdQuery;

    @Value("${queries.sql.group-post-dao.select.group-post-by-group-id}$")
    private String selectGroupPostByGroupIdQuery;

    @Value("${queries.sql.group-post-dao.select.group-post-by-user-id}$")
    private String selectGroupPostByUserIdQuery;

    @Value("${queries.sql.group-post-dao.update.group-post}$")
    private String updateGroupPostQuery;

    @Value("${queries.sql.group-post-dao.delete.group-post}$")
    private String deleteGroupPostQuery;

    @Override
    public GroupPost add(GroupPost groupPost) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertGroupPostQuery, id, groupPost.getGroupId(),
                groupPost.getUserId(), groupPost.getTitle(), groupPost.getText());
        return GroupPost.createFull(id, groupPost.getGroupId(), groupPost.getUserId(),
                groupPost.getTitle(), groupPost.getText());
    }

    @Override
    public GroupPost findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectGroupPostByIdQuery, this::mapperGroupPostFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-post with id: " + id);
        }
    }

    @Override
    public List<GroupPost> findByGroupId(UUID groupId) {
        try {
            return jdbcTemplate.query(selectGroupPostByGroupIdQuery, this::mapperGroupPostFromRs, groupId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-posts with group id: " + groupId);
        }
    }

    @Override
    public List<GroupPost> findByUserId(UUID userId) {
        try {
            return jdbcTemplate.query(selectGroupPostByUserIdQuery, this::mapperGroupPostFromRs, userId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-posts with user id: " + userId);
        }
    }

    @Override
    public GroupPost update(GroupPost groupPost, UUID id) {
        jdbcTemplate.update(updateGroupPostQuery, groupPost.getTitle(), groupPost.getText(), id);
        return GroupPost.createFull(groupPost.getId(), groupPost.getGroupId(), groupPost.getUserId(),
                groupPost.getTitle(), groupPost.getText());
    }

    @Override
    public void delete(UUID groupPostId) {
        jdbcTemplate.update(deleteGroupPostQuery, groupPostId);
    }

    private GroupPost mapperGroupPostFromRs(ResultSet rs, int rowNum) throws SQLException {
        return GroupPost.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("group_id"),
                (UUID) rs.getObject("user_id"),
                rs.getString("title"),
                rs.getString("text")
        );
    }
}
