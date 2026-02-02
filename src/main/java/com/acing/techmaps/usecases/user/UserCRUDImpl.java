package com.acing.techmaps.usecases.user;

import com.acing.techmaps.domain.entities.user.Position;
import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.security.utils.SecurityUtils;
import com.acing.techmaps.usecases.dashboard.gateway.DashboardDAO;
import com.acing.techmaps.usecases.user.gateway.UserDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.acing.techmaps.web.model.user.request.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserCRUDImpl implements UserCRUD {
    private final UserDAO userDAO;
    private final DashboardDAO dashboardDAO;
    private final SecurityUtils securityUtils;

    @Override
    public User registerNewUser(UserRequest request) {
        User user = userDAO.add(request.toUser());
        dashboardDAO.add(user.getId());
        return user;
    }

    @Override
    public User getById(UUID id) {
        return userDAO.findById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User getByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User getByPosition(String position) {
        return userDAO.findByPosition(Position.valueOf(position));
    }

    @Override
    public User update(UserRequest request, UUID userId) {
        if (!securityUtils.hasDestructivePermissions(userId)) {
            throw new HttpException(HttpStatus.FORBIDDEN, "You are not authorized to update this user");
        }

        if (!userDAO.userExists(userId)) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find user with id: " + userId);
        }
        return userDAO.update(request.toUser().createWithId(userId));
    }

    @Override
    public User updatePosition(String position, UUID userId) {
        if (!userDAO.userExists(userId)) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find user with id: " + userId);
        }
        return userDAO.updatePosition(Position.valueOf(position), userId);
    }

    @Override
    public void deleteUser(UUID id) {
        if (!securityUtils.hasDestructivePermissions(id)) {
            throw new HttpException(HttpStatus.FORBIDDEN, "You are not authorized to delete this user");
        }

        User user = userDAO.findById(id);
        userDAO.deleteUser(user);
    }
}