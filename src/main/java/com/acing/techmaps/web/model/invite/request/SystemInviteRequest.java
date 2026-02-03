package com.acing.techmaps.web.model.invite.request;

import com.acing.techmaps.domain.entities.invite.SystemInvite;
import com.acing.techmaps.domain.entities.user.Position;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record SystemInviteRequest(
        @NotBlank @Email String email,
        @NotBlank String code,
        @NotBlank String position,
        @NotNull @Future Timestamp expiresAt
) {
    public SystemInvite toSystemInvite() {
        return SystemInvite.fromRequest(email, code, Position.valueOf(position), expiresAt);
    }
}
