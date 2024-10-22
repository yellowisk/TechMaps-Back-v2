package com.acing.techmaps.usecases.group.gateway;

import com.acing.techmaps.domain.entities.group.GroupUser;

import java.util.List;
import java.util.UUID;

public interface GroupUserDAO {
    GroupUser add(GroupUser groupUser);
    GroupUser findById(UUID id);
    List<GroupUser> findByGroup(UUID groupId);
    List<GroupUser> findByUser(UUID  userId);
    GroupUser updateRole(GroupUser groupUser);
    void delete(GroupUser groupUser);
    Boolean groupUserExists(UUID id);
}
