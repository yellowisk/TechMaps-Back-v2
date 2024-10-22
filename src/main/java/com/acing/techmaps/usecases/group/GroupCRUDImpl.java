package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.Group;
import com.acing.techmaps.usecases.group.gateway.GroupDAO;
import com.acing.techmaps.web.model.group.request.GroupRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class GroupCRUDImpl implements GroupCRUD {
    private final GroupDAO groupDAO;

    @Override
    public Group create(GroupRequest request) {
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
