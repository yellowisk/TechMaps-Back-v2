package com.acing.techmaps.domain.entities.group;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GroupUser {
    private UUID id;
    private UUID groupId;
    private UUID userId;
    private Role role;

    public GroupUser(UUID id, UUID groupId, UUID userId, Role role) {
        this.id = id;
        this.groupId = groupId;
        this.userId = userId;
        this.role = role;
    }

    public GroupUser(UUID groupId, UUID userId, Role role) {
        this.groupId = groupId;
        this.userId = userId;
        this.role = role;
    }

    public static GroupUser createFull(UUID id, UUID groupId, UUID userId, Role role) {
        return new GroupUser(id, groupId, userId, role);
    }

    public static GroupUser fromRequest(UUID groupId, UUID userId, String role) {
        return new GroupUser(groupId, userId, Role.valueOf(role));
    }
}