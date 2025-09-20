package com.acing.techmaps.usecases.invite;

import com.acing.techmaps.domain.entities.invite.GroupInvite;
import com.acing.techmaps.usecases.invite.gateway.GroupInviteDAO;
import com.acing.techmaps.web.model.invite.request.GroupInviteRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class GroupInviteCRUDImpl implements GroupInviteCRUD {
    private final GroupInviteDAO groupInviteDAO;

    @Override
    public GroupInvite create(GroupInviteRequest request) {
        return groupInviteDAO.add(request.toGroupInvite());
    }

    @Override
    public GroupInvite getById(UUID id) {
        return groupInviteDAO.findById(id);
    }

    @Override
    public GroupInvite getByEmail(String email) {
        return groupInviteDAO.findByEmail(email);
    }

    @Override
    public GroupInvite getByCode(String code) {
        return groupInviteDAO.findByCode(code);
    }

    @Override
    public List<GroupInvite> getAll() {
        return groupInviteDAO.findAll();
    }

    @Override
    public void delete(UUID id) {
        GroupInvite groupInvite = groupInviteDAO.findById(id);
        groupInviteDAO.delete(groupInvite);
    }
}
