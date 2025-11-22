package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.group.Role;
import com.acing.techmaps.domain.entities.invite.GroupInvite;
import com.acing.techmaps.usecases.invite.gateway.GroupInviteDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class GroupInviteDAOImpl implements GroupInviteDAO {
    private JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.group-invite-dao.insert.group-invite}")
    private String insertGroupInviteQuery;
    @Value("${queries.sql.group-invite-dao.select.group-invite-by-id}}")
    private String selectGroupInviteByIdQuery;
    @Value("${queries.sql.group-invite-dao.select.group-invite-by-email}}")
    private String selectGroupInviteByEmailQuery;
    @Value("${queries.sql.group-invite-dao.select.group-invite-by-code}}")
    private String selectGroupInviteByCodeQuery;
    @Value("${queries.sql.group-invite-dao.select.all}}")
    private String selectAllGroupInvitesQuery;
    @Value("${queries.sql.group-invite-dao.delete.group-invite}}")
    private String deleteGroupInviteByIdQuery;
    @Value("${queries.sql.group-invite-dao.exists.group-invite-by-id}}")
    private String existsGroupInviteByIdQuery;

    @Override
    public GroupInvite add(GroupInvite invite) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertGroupInviteQuery, id, invite.getGroupId(), invite.getEmail(),
                invite.getCode(), invite.getRole().name(),
                invite.getExpiresAt(), invite.getCreatedAt());
        return GroupInvite.createFull(id, invite.getGroupId(), invite.getEmail(), invite.getCode(),
                invite.getRole(), invite.getExpiresAt(), invite.getCreatedAt());
    }

    @Override
    public GroupInvite findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectGroupInviteByIdQuery, this::mapperGroupInviteFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-invite with id: " + id);
        }
    }

    @Override
    public GroupInvite findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(selectGroupInviteByEmailQuery, this::mapperGroupInviteFromRs, email);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-invite with email: " + email);
        }
    }

    @Override
    public GroupInvite findByCode(String code) {
        try {
            return jdbcTemplate.queryForObject(selectGroupInviteByCodeQuery, this::mapperGroupInviteFromRs, code);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group-invite with code: " + code);
        }
    }

    @Override
    public List<GroupInvite> findAll() {
        return jdbcTemplate.query(selectAllGroupInvitesQuery, this::mapperGroupInviteFromRs);
    }

    @Override
    public void delete(GroupInvite invite) {
        jdbcTemplate.update(deleteGroupInviteByIdQuery, invite.getId());
    }

    @Override
    public Boolean GroupInviteExists(UUID id) {
        return jdbcTemplate.queryForObject(existsGroupInviteByIdQuery, Boolean.class, id);
    }

    private GroupInvite mapperGroupInviteFromRs(java.sql.ResultSet rs, int rowNum) throws SQLException {
        return GroupInvite.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("group_id"),
                rs.getString("email"),
                rs.getString("code"),
                Role.valueOf(rs.getString("position")),
                rs.getTimestamp("expires_at"),
                rs.getTimestamp("created_at")
        );
    }
}
