package com.acing.techmaps.usecases.user;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.web.model.user.request.UserRequest;

import java.util.UUID;

public interface UserCRUD {
    User registerNewUser(UserRequest request);

    User getById(UUID id);

    User getByEmail(String email);

    User getByUsername(String username);

    User update(UserRequest request, UUID id);
}