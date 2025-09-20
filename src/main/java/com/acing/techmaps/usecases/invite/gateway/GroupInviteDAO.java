package com.acing.techmaps.usecases.invite.gateway;

import com.acing.techmaps.domain.entities.invite.GroupInvite;

import java.util.List;
import java.util.UUID;

public interface GroupInviteDAO {
    GroupInvite add(GroupInvite invite);
    GroupInvite findById(UUID id);
    GroupInvite findByEmail(String email);
    GroupInvite findByCode(String code);
    List<GroupInvite> findAll();
    void delete(GroupInvite invite);
    Boolean GroupInviteExists(UUID id);
}
