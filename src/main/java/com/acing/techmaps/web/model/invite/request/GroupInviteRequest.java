package com.acing.techmaps.web.model.invite.request;

import com.acing.techmaps.domain.entities.group.Role;
import com.acing.techmaps.domain.entities.invite.GroupInvite;

import java.sql.Timestamp;
import java.util.UUID;

public record GroupInviteRequest(UUID groupId, String email, String code, String role, Timestamp expiresAt) {
    public GroupInvite toGroupInvite() {
        return GroupInvite.fromRequest(groupId, email, code, Role.valueOf(role), expiresAt);
    }
}
