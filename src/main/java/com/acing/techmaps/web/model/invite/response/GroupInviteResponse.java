package com.acing.techmaps.web.model.invite.response;

import com.acing.techmaps.domain.entities.group.Role;
import com.acing.techmaps.domain.entities.invite.GroupInvite;

import java.sql.Timestamp;
import java.util.UUID;

public record GroupInviteResponse(UUID id, UUID groupId, String email, String code, Role role, Timestamp expiresAt, Timestamp createdAt) {
    public static GroupInviteResponse fromGroupInvite(GroupInvite groupInvite) {
        return new GroupInviteResponse(
                groupInvite.getId(),
                groupInvite.getGroupId(),
                groupInvite.getEmail(),
                groupInvite.getCode(),
                groupInvite.getRole(),
                groupInvite.getExpiresAt(),
                groupInvite.getCreatedAt()
        );
    }
}
