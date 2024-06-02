package com.acing.techmaps.usecases.user;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.usecases.dashboard.gateway.DashboardDAO;
import com.acing.techmaps.usecases.user.gateway.UserDAO;
import com.acing.techmaps.web.model.user.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCRUDImpl implements UserCRUD {
    private final UserDAO userDAO;
    private final DashboardDAO dashboardDAO;

    public UserCRUDImpl(UserDAO userDAO, DashboardDAO dashboardDAO) {
        this.userDAO = userDAO;
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public User registerNewUser(UserRequest request) {
        User user = userDAO.add(request.toUser());
        dashboardDAO.add(user.getId());
        return user;
    }

    @Override
    public User getById(UUID id) {
        return userDAO.findById(id).get();
    }

    @Override
    public User getByEmail(String email) {
        return userDAO.findByEmail(email).get();
    }

    @Override
    public User getByUsername(String username) {
        return userDAO.findByUsername(username).get();
    }

    @Override
    public User update(UserRequest request, UUID userId) {
        if (!userDAO.userExists(userId)) {
            throw new RuntimeException("Couldn't find user with id: " + userId);
        }

        User user = userDAO.findById(userId).get();
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(request.password());
        return userDAO.update(user);
    }

}
