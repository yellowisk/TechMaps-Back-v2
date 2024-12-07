package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.GroupPost;
import com.acing.techmaps.usecases.group.gateway.GroupPostDAO;
import com.acing.techmaps.web.model.group.request.GroupPostRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupPostCRUDImpl implements GroupPostCRUD {
    private final GroupPostDAO groupPostDAO;

    public GroupPostCRUDImpl(GroupPostDAO groupPostDAO) {
        this.groupPostDAO = groupPostDAO;
    }

    @Override
    public GroupPost create(GroupPostRequest request) {
        return groupPostDAO.add(request.toGroupPost());
    }

    @Override
    public GroupPost getById(UUID id) {
        return groupPostDAO.findById(id);
    }

    @Override
    public List<GroupPost> getByGroupId(UUID groupId) {
        return groupPostDAO.findByGroupId(groupId);
    }

    @Override
    public List<GroupPost> getByUserId(UUID userId) {
        return groupPostDAO.findByUserId(userId);
    }

    @Override
    public GroupPost updateGroupPost(GroupPostRequest request, UUID id) {
        return groupPostDAO.update(request.toGroupPost(), id);
    }

    @Override
    public void delete(UUID id) {
        GroupPost groupPost = groupPostDAO.findById(id);
        groupPostDAO.delete(groupPost.getId());
    }
}
