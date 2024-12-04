package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.Group;
import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.security.utils.SecurityUtils;
import com.acing.techmaps.usecases.group.gateway.GroupDAO;
import com.acing.techmaps.usecases.group.gateway.GroupUserDAO;
import com.acing.techmaps.web.model.group.request.GroupRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GroupCRUDImpl implements GroupCRUD {
    private final GroupDAO groupDAO;
    private final GroupUserDAO groupUserDAO;
    private final SecurityUtils securityUtils;

    @Override
    public Group create(GroupRequest request) {
        User user = securityUtils.getCurrentUser();
        Group group = request.toGroup();
        if (group.getParentId() == null) {
            return groupDAO.add(group);
        } else {
            boolean isOwnerOrCollaborator = groupUserDAO.findByUser(user.getId()).stream()
                    .anyMatch(groupUser -> groupUser.getGroupId().equals(group.getParentId()) &&
                            (groupUser.getRole().equals("OWNER") || groupUser.getRole().equals("COLLABORATOR")));
            if (!isOwnerOrCollaborator) {
                throw new IllegalArgumentException("User is not owner or collaborator of parent group");
            } else {
                return groupDAO.add(group);
            }
        }
    }

    @Override
    public Group getById(UUID id) {
        return groupDAO.findById(id);
    }

    @Override
    public Group getByName(String name) {
        return groupDAO.findByName(name);
    }

    @Override
    public Group updateGroup(GroupRequest request, UUID id) {
        Group group = request.toGroup();
        group.setId(id);
        return groupDAO.update(group);
    }

    @Override
    public List<Group> findGroupHierarchy(UUID groupId) {
        return groupDAO.findGroupHierarchy(groupId);
    }

    @Override
    public Group findParent(UUID groupId) {
        List<Group> hierarchy = findGroupHierarchy(groupId);
        System.out.println(hierarchy);
        Map<UUID, Group> groupMap = hierarchy.stream()
                .collect(Collectors.toMap(Group::getId, group -> group));

        System.out.println(groupMap);

        Group childGroup = groupMap.get(groupId);

        if (childGroup == null) {
            System.out.println("Child group not found in any hierarchy");
            return null;
        }

        return groupMap.get(childGroup.getParentId());
    }
}
