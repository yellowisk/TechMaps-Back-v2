package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.group.GroupComment;
import com.acing.techmaps.domain.entities.group.GroupPost;
import com.acing.techmaps.usecases.group.gateway.GroupCommentDAO;
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
public class GroupCommentDAOImpl implements GroupCommentDAO {
    private final JdbcTemplate jdbcTemplate;

    public GroupCommentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.group-comment-dao.insert.group-comment}$")
    private String insertGroupCommentQuery;

    @Value("${queries.sql.group-comment-dao.select.group-comment-by-id}$")
    private String selectGroupCommentByIdQuery;

    @Value("${queries.sql.group-comment-dao.select.group-comment-by-group-post-id}$")
    private String selectGroupCommentByGroupPostIdQuery;

    @Value("${queries.sql.group-comment-dao.select.group-comment-by-user-id}$")
    private String selectGroupCommentByUserIdQuery;

    @Value("${queries.sql.group-comment-dao.update.group-comment}$")
    private String updateGroupCommentQuery;

    @Value("${queries.sql.group-comment-dao.delete.group-comment}$")
    private String deleteGroupCommentQuery;

    @Override
    public GroupComment add(GroupComment groupComment) {
        UUID id = Generators.timeBasedGenerator().generate();
        jdbcTemplate.update(insertGroupCommentQuery, id, groupComment.getGroupPostId(),
                groupComment.getUserId(), groupComment.getText());
        return GroupComment.createFull(id, groupComment.getGroupPostId(), groupComment.getUserId(), groupComment.getText());
    }

    @Override
    public GroupComment findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectGroupCommentByIdQuery, this::mapperGroupCommentFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-comment with id: " + id);
        }
    }

    @Override
    public List<GroupComment> findByGroupPostId(UUID groupPostId) {
        try {
            return jdbcTemplate.query(selectGroupCommentByGroupPostIdQuery, this::mapperGroupCommentFromRs, groupPostId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-comment with group-post-id: " + groupPostId);
        }
    }

    @Override
    public List<GroupComment> findByUserId(UUID userId) {
        try {
            return jdbcTemplate.query(selectGroupCommentByUserIdQuery, this::mapperGroupCommentFromRs, userId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-comment with user-id: " + userId);
        }
    }

    @Override
    public GroupComment update(GroupComment groupComment, UUID id) {
        jdbcTemplate.update(updateGroupCommentQuery, groupComment.getText(), id);
        return groupComment;
    }

    @Override
    public void delete(UUID groupPostId) {
        jdbcTemplate.update(deleteGroupCommentQuery, groupPostId);
    }

    private GroupComment mapperGroupCommentFromRs(ResultSet rs, int rowNum) throws SQLException {
        return GroupComment.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("group_post_id"),
                (UUID) rs.getObject("user_id"),
                rs.getString("text")
        );
    }
}
