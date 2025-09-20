package com.acing.techmaps.web.model.invite.request;

import com.acing.techmaps.domain.entities.invite.SystemInvite;
import com.acing.techmaps.domain.entities.user.Position;

import java.sql.Timestamp;

public record SystemInviteRequest(String email, String code, String position, Timestamp expiresAt) {
    public SystemInvite toSystemInvite() {
        return SystemInvite.fromRequest(email, code, Position.valueOf(position), expiresAt);
    }
}
