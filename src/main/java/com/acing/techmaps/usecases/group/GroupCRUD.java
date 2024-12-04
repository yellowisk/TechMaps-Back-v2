package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.Group;
import com.acing.techmaps.web.model.group.request.GroupRequest;

import java.util.List;
import java.util.UUID;

public interface GroupCRUD {
    Group create(GroupRequest request);
    Group getById(UUID id);
    Group getByName(String name);
    Group updateGroup(GroupRequest request, UUID id);
    Group findParent(UUID groupId);
    List<Group> findGroupHierarchy(UUID groupId);
}
