package com.acing.techmaps.web.model.invite.request;

import com.acing.techmaps.domain.entities.group.Role;
import com.acing.techmaps.domain.entities.invite.GroupInvite;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public record GroupInviteRequest(
        @NotNull UUID groupId,
        @NotBlank @Email String email,
        @NotBlank String code,
        @NotBlank String role,
        @NotNull @Future Timestamp expiresAt
) {
    public GroupInvite toGroupInvite() {
        return GroupInvite.fromRequest(groupId, email, code, Role.valueOf(role), expiresAt);
    }
}
