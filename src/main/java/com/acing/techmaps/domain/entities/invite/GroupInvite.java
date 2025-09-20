package com.acing.techmaps.domain.entities.invite;

import com.acing.techmaps.domain.entities.group.Role;
import com.acing.techmaps.domain.entities.user.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class GroupInvite {
    private UUID id;
    private UUID groupId;
    private String email;
    private String code;
    private Role role;
    private Timestamp expiresAt;
    private Timestamp createdAt;

    public GroupInvite(UUID groupId, String email, String code, Role role, Timestamp expiresAt) {
        this.groupId = groupId;
        this.email = email;
        this.code = code;
        this.role = role;
        this.expiresAt = expiresAt;
    }

    public static GroupInvite createFull(UUID id, UUID groupId, String email, String code, Role role, Timestamp expiresAt, Timestamp createdAt) {
        return new GroupInvite(id, groupId, email, code, role, expiresAt, createdAt);
    }

    public static GroupInvite fromRequest(UUID groupId, String email, String code, Role role, Timestamp expiresAt) {
        return new GroupInvite(groupId, email, code, role, expiresAt);
    }
}
