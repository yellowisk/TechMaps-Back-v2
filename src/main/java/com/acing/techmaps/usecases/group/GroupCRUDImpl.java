package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.Group;
import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.security.utils.SecurityUtils;
import com.acing.techmaps.usecases.group.gateway.GroupDAO;
import com.acing.techmaps.web.model.group.request.GroupRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class GroupCRUDImpl implements GroupCRUD {
    private final GroupDAO groupDAO;
    private final SecurityUtils securityUtils;

    @Override
    public Group create(GroupRequest request) {
        User user = securityUtils.getCurrentUser();
        /*TODO: figure out way of find the parent group of the new group...
         There are cases where this new group may be a sub-group of another group
        */
        //TODO: check if the current user has permission to create a group through the GroupUserDAO
        //TODO: find out where to put all this logic
        return groupDAO.add(request.toGroup());
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
}
