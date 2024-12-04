package com.acing.techmaps.usecases.group.gateway;

import com.acing.techmaps.domain.entities.group.Group;

import java.util.List;
import java.util.UUID;

public interface GroupDAO {
    Group add(Group group);
    Group findById(UUID id);
    Group findByName(String name);
    List<Group> findGroupHierarchy(UUID groupId);

    Group update(Group group);
    Boolean groupExists(UUID id);
}
