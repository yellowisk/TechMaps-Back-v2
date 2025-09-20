package com.acing.techmaps.usecases.invite.gateway;

import com.acing.techmaps.domain.entities.invite.SystemInvite;

import java.util.List;
import java.util.UUID;

public interface SystemInviteDAO {
    SystemInvite add(SystemInvite invite);
    SystemInvite findById(UUID id);
    SystemInvite findByEmail(String email);
    SystemInvite findByCode(String code);
    List<SystemInvite> findAll();
    void delete(SystemInvite invite);
    Boolean SystemInviteExists(UUID id);
}
