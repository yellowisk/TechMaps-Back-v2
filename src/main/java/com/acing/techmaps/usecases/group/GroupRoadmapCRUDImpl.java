package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.GroupRoadmap;
import com.acing.techmaps.usecases.group.gateway.GroupRoadmapDAO;
import com.acing.techmaps.web.model.group.request.GroupRoadmapRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupRoadmapCRUDImpl implements GroupRoadmapCRUD {
    private final GroupRoadmapDAO groupRoadmapDAO;

    public GroupRoadmapCRUDImpl(GroupRoadmapDAO groupRoadmapDAO) {
        this.groupRoadmapDAO = groupRoadmapDAO;
    }

    @Override
    public GroupRoadmap create(GroupRoadmapRequest request) {
        return groupRoadmapDAO.add(request.toGroupRoadmap());
    }

    @Override
    public GroupRoadmap getById(UUID id) {
        return groupRoadmapDAO.findById(id);
    }

    @Override
    public List<GroupRoadmap> getByGroupId(UUID groupId) {
        return groupRoadmapDAO.findByGroupId(groupId);
    }

    @Override
    public List<GroupRoadmap> getByRoadmapId(UUID roadmapId) {
        return groupRoadmapDAO.findByRoadmapId(roadmapId);
    }

    @Override
    public void delete(UUID id) {
        GroupRoadmap groupRoadmap = groupRoadmapDAO.findById(id);
        groupRoadmapDAO.delete(groupRoadmap);
    }
}
