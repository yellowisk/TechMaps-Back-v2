package com.acing.techmaps.web.model.user.request;

import com.acing.techmaps.domain.entities.user.User;

public record UserRequest(String email, String username, String password) {

    public UserRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User toUser() {
        return User.fromRequest(email, username, password);
    }

}