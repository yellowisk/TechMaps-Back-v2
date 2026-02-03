package com.acing.techmaps.web.model.group.request;

import com.acing.techmaps.domain.entities.group.GroupUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record GroupUserRequest(
        @NotNull UUID groupId,
        @NotNull UUID userId,
        @NotBlank String role
) {

    public GroupUser toGroupUser() {
        return GroupUser.fromRequest(groupId, userId, role);
    }
}
