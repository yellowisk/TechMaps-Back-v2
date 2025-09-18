package com.acing.techmaps.usecases.user.gateway;

import com.acing.techmaps.domain.entities.user.Position;
import com.acing.techmaps.domain.entities.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserDAO {
    User add(User user);

    User findById(UUID id);

    User findByEmail(String email);

    User findByUsername(String email);

    User findByPosition(Position position);

    User update(User user);

    User updatePosition(Position position, UUID id);
    void deleteUser(User user);

    Boolean userExists(UUID id);
}
