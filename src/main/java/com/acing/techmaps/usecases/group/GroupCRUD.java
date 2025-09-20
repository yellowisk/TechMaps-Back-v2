package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.Group;
import com.acing.techmaps.web.model.group.request.GroupRequest;

import java.util.List;
import java.util.UUID;

public interface GroupCRUD {
    Group create(GroupRequest request);
    Group getById(UUID id);
    Group getByName(String name);
    List<Group> getByCreatorId(UUID creatorId);
    List<Group> getByParentId(UUID parentId);
    Group findRoot(UUID groupId);
    List<Group> findGroupHierarchy(UUID groupId);
    Group updateGroup(GroupRequest request, UUID id);
    void delete(UUID groupId);
}
