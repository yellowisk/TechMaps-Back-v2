package com.acing.techmaps.usecases.invite;

import com.acing.techmaps.domain.entities.invite.GroupInvite;
import com.acing.techmaps.web.model.invite.request.GroupInviteRequest;

import java.util.List;
import java.util.UUID;

public interface GroupInviteCRUD {
    GroupInvite create(GroupInviteRequest request);
    GroupInvite getById(UUID id);
    GroupInvite getByEmail(String email);
    GroupInvite getByCode(String code);
    List<GroupInvite> getAll();
    void delete(UUID id);
}
