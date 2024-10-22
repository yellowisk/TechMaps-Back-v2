package com.acing.techmaps.usecases.group;


import com.acing.techmaps.domain.entities.group.GroupUser;
import com.acing.techmaps.web.model.group.request.GroupUserRequest;

import java.util.List;
import java.util.UUID;

public interface GroupUserCRUD {
    GroupUser create (GroupUserRequest request);
    GroupUser getById(UUID id);
    List<GroupUser> getByGroupId(UUID groupId);
    List<GroupUser> getByUserId(UUID userId);
    GroupUser updateRole(UUID id, String role);
    void delete(UUID id);
}
