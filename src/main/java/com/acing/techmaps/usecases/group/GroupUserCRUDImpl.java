package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.GroupUser;
import com.acing.techmaps.domain.entities.group.Role;
import com.acing.techmaps.usecases.group.gateway.GroupUserDAO;
import com.acing.techmaps.web.model.group.request.GroupUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupUserCRUDImpl implements GroupUserCRUD {
    private final GroupUserDAO groupUserDAO;

    public GroupUserCRUDImpl(GroupUserDAO groupUserDAO) {
        this.groupUserDAO = groupUserDAO;
    }

    @Override
    public GroupUser create(GroupUserRequest request) {
        return groupUserDAO.add(request.toGroupUser());
    }

    @Override
    public GroupUser getById(UUID id) {
        return groupUserDAO.findById(id);
    }

    @Override
    public List<GroupUser> getByGroupId(UUID groupId) {
        return groupUserDAO.findByGroup(groupId);
    }

    @Override
    public List<GroupUser> getByUserId(UUID userId) {
        return groupUserDAO.findByUser(userId);
    }

    @Override
    public GroupUser updateRole(UUID id, String role) {
        GroupUser groupUser = groupUserDAO.findById(id);
        groupUser.setRole(Role.valueOf(role));
        return groupUserDAO.updateRole(groupUser);
    }

    @Override
    public void delete(UUID id) {
        GroupUser groupUser = groupUserDAO.findById(id);
        groupUserDAO.delete(groupUser);
    }
}
