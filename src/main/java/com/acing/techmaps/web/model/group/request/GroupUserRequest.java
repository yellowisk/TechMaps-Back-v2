package com.acing.techmaps.web.model.group.request;

import com.acing.techmaps.domain.entities.group.GroupUser;

import java.util.UUID;

public record GroupUserRequest(UUID groupId, UUID userId, String role) {

    public GroupUser toGroupUser() {
        return GroupUser.fromRequest(groupId, userId, role);
    }
}
