package com.acing.techmaps.usecases.invite;

import com.acing.techmaps.domain.entities.invite.SystemInvite;
import com.acing.techmaps.security.utils.SecurityUtils;
import com.acing.techmaps.usecases.invite.gateway.SystemInviteDAO;
import com.acing.techmaps.web.model.invite.request.SystemInviteRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SystemInviteCRUDImpl implements SystemInviteCRUD {
    private final SystemInviteDAO systemInviteDAO;

    @Override
    public SystemInvite create(SystemInviteRequest request) {
        return systemInviteDAO.add(request.toSystemInvite());
    }

    @Override
    public SystemInvite getById(UUID id) {
        return systemInviteDAO.findById(id);
    }

    @Override
    public SystemInvite getByEmail(String email) {
        return systemInviteDAO.findByEmail(email);
    }

    @Override
    public SystemInvite getByCode(String code) {
        return systemInviteDAO.findByCode(code);
    }

    @Override
    public List<SystemInvite> getAll() {
        return systemInviteDAO.findAll();
    }

    @Override
    public void delete(UUID id) {
        SystemInvite systemInvite = systemInviteDAO.findById(id);
        systemInviteDAO.delete(systemInvite);
    }
}
