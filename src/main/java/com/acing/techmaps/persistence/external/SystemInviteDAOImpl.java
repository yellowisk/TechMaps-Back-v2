package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.invite.SystemInvite;
import com.acing.techmaps.domain.entities.user.Position;
import com.acing.techmaps.usecases.invite.gateway.SystemInviteDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public class SystemInviteDAOImpl implements SystemInviteDAO {
    private JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.system-invite-dao.insert.system-invite}")
    private String insertSystemInviteQuery;
    @Value("${queries.sql.system-invite-dao.select.system-invite-by-id}")
    private String selectSystemInviteByIdQuery;
    @Value("${queries.sql.system-invite-dao.select.system-invite-by-email}")
    private String selectSystemInviteByEmailQuery;
    @Value("${queries.sql.system-invite-dao.select.system-invite-by-code}")
    private String selectSystemInviteByCodeQuery;
    @Value("${queries.sql.system-invite-dao.select.all}")
    private String selectAllSystemInvitesQuery;
    @Value("${queries.sql.system-invite-dao.delete.system-invite}")
    private String deleteSystemInviteByIdQuery;
    @Value("${queries.sql.system-invite-dao.exists.system-invite-by-id}")
    private String existsSystemInviteByIdQuery;

    @Override
    public SystemInvite add(SystemInvite invite) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertSystemInviteQuery, id, invite.getEmail(),
                invite.getCode(), invite.getPosition().name(),
                invite.getExpiresAt(), Timestamp.from(Instant.now()));
        return SystemInvite.createFull(id, invite.getEmail(), invite.getCode(),
                invite.getPosition(), invite.getExpiresAt(), invite.getCreatedAt());
    }

    @Override
    public SystemInvite findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectSystemInviteByIdQuery, this::mapperSystemInviteFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, STR."Could not find system-invite with id: \{id}");
        }
    }

    @Override
    public SystemInvite findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(selectSystemInviteByEmailQuery, this::mapperSystemInviteFromRs, email);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, STR."Could not find system-invite with email: \{email}");
        }
    }

    @Override
    public SystemInvite findByCode(String code) {
        try {
            return jdbcTemplate.queryForObject(selectSystemInviteByCodeQuery, this::mapperSystemInviteFromRs, code);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, STR."Could not find system-invite with code: \{code}");
        }
    }

    @Override
    public List<SystemInvite> findAll() {
        return jdbcTemplate.query(selectAllSystemInvitesQuery, this::mapperSystemInviteFromRs);
    }

    @Override
    public void delete(SystemInvite systemInvite) {
        jdbcTemplate.update(deleteSystemInviteByIdQuery, systemInvite);
    }

    @Override
    public Boolean SystemInviteExists(UUID id) {
        return jdbcTemplate.queryForObject(existsSystemInviteByIdQuery, Boolean.class, id);
    }

    private SystemInvite mapperSystemInviteFromRs(ResultSet rs, int rowNum) throws SQLException {
        return SystemInvite.createFull(
                (UUID) rs.getObject("id"),
                rs.getString("email"),
                rs.getString("code"),
                Position.valueOf(rs.getString("position")),
                rs.getTimestamp("expires_at"),
                rs.getTimestamp("created_at")
        );
    }
}
