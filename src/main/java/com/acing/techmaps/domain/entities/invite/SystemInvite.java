package com.acing.techmaps.domain.entities.invite;

import com.acing.techmaps.domain.entities.user.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class SystemInvite {
    private UUID id;
    private String email;
    private String code;
    private Position position;
    private Timestamp expiresAt;
    private Timestamp createdAt;

    public SystemInvite(String email, String code, Position position, Timestamp expiresAt) {
        this.email = email;
        this.code = code;
        this.position = position;
        this.expiresAt = expiresAt;
    }

    public static SystemInvite createFull(UUID id, String email, String code, Position position, Timestamp expiresAt, Timestamp createdAt) {
        return new SystemInvite(id, email, code, position, expiresAt, createdAt);
    }

    public static SystemInvite fromRequest(String email, String code, Position position, Timestamp expiresAt) {
        return new SystemInvite(email, code, position, expiresAt);
    }
}
