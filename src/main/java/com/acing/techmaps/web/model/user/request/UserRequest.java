package com.acing.techmaps.web.model.user.request;

import com.acing.techmaps.domain.entities.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record UserRequest(String email, String username, String password) {

    public User toUser() {
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        return User.fromRequest(email, username, encryptedPassword);
    }

}