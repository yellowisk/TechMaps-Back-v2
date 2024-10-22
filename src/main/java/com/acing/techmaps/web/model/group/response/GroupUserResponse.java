package com.acing.techmaps.web.model.group.response;

import com.acing.techmaps.domain.entities.group.GroupUser;
import com.acing.techmaps.domain.entities.group.Role;

import java.util.UUID;

public record GroupUserResponse(UUID id, UUID groupId, UUID userId, Role role) {
    public static GroupUserResponse fromGroupUser(GroupUser groupUser) {
        return new GroupUserResponse(
                groupUser.getId(), groupUser.getGroupId(),
                groupUser.getUserId(), groupUser.getRole()
        );
    }
}
