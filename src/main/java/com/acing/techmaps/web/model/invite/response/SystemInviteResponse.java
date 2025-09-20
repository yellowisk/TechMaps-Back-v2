package com.acing.techmaps.web.model.invite.response;

import com.acing.techmaps.domain.entities.invite.SystemInvite;
import com.acing.techmaps.domain.entities.user.Position;

import java.sql.Timestamp;
import java.util.UUID;

public record SystemInviteResponse(UUID id, String email, String code, Position position, Timestamp expiresAt, Timestamp createdAt) {
    public static SystemInviteResponse fromSystemInvite(SystemInvite systemInvite) {
        return new SystemInviteResponse(
                systemInvite.getId(),
                systemInvite.getEmail(),
                systemInvite.getCode(),
                systemInvite.getPosition(),
                systemInvite.getExpiresAt(),
                systemInvite.getCreatedAt()
        );
    }
}
