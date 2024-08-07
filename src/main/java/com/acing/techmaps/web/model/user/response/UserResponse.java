package com.acing.techmaps.web.model.user.response;

import com.acing.techmaps.domain.entities.user.User;

import java.util.UUID;

public record UserResponse(UUID id, String email, String username) {

    public static UserResponse createFromUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername()
        );
    }
}
