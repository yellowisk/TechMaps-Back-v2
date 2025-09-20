package com.acing.techmaps.usecases.invite;

import com.acing.techmaps.domain.entities.invite.SystemInvite;
import com.acing.techmaps.web.model.invite.request.SystemInviteRequest;

import java.util.List;
import java.util.UUID;

public interface SystemInviteCRUD {
    SystemInvite create(SystemInviteRequest request);
    SystemInvite getById(UUID id);
    SystemInvite getByEmail(String email);
    SystemInvite getByCode(String code);
    List<SystemInvite> getAll();
    void delete(UUID id);
}