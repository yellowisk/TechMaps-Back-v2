package com.acing.techmaps.usecases.group.gateway;

import com.acing.techmaps.domain.entities.group.GroupRoadmap;

import java.util.List;
import java.util.UUID;

public interface GroupRoadmapDAO {
    GroupRoadmap add(GroupRoadmap groupRoadmap);
    GroupRoadmap findById(UUID id);
    List<GroupRoadmap> findByGroupId(UUID groupId);
    List<GroupRoadmap> findByRoadmapId(UUID roadmapId);
    void delete(GroupRoadmap groupRoadmap);
    Boolean groupRoadmapExists(UUID id);
}
