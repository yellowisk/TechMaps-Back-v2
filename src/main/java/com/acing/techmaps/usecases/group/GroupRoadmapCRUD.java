package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.GroupRoadmap;
import com.acing.techmaps.web.model.group.request.GroupRoadmapRequest;

import java.util.List;
import java.util.UUID;

public interface GroupRoadmapCRUD {
    GroupRoadmap create (GroupRoadmapRequest request);
    GroupRoadmap getById(UUID id);
    List<GroupRoadmap> getByGroupId(UUID groupId);
    List<GroupRoadmap> getByRoadmapId(UUID roadmapId);
    void delete(UUID id);
}
