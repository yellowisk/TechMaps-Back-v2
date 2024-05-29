package com.acing.techmaps.usecases.user;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.usecases.user.gateway.UserDAO;
import com.acing.techmaps.web.model.user.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserCRUDImpl implements UserCRUD {
    private final UserDAO userDAO;
    public UserCRUDImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public User registerNewUser(UserRequest request) {
        return userDAO.addNewUser(request.toUser());
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
    public User getByUsername(String username) {;
        return userDAO.findByUsername(username).get();
    }

    @Override
    public User update(UserRequest request, UUID userId) {
        if(!userDAO.userExists(userId)) {
            throw new RuntimeException("Couldn't find user with id: " + userId);
        }

        User user = userDAO.findById(userId).get();
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(request.password());
        return userDAO.update(user);
    }

}
