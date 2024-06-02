package com.acing.techmaps.usecases.user.gateway;

import com.acing.techmaps.domain.entities.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserDAO {
    User add(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String email);

    User update(User user);

    Boolean userExists(UUID id);
}
