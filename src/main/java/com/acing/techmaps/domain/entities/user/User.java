package com.acing.techmaps.domain.entities.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID id;
    private String email;
    private String username;
    private String password;

    public User(UUID id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public static User createFull(UUID id, String email, String username, String password) {
        return new User(id, email, username, password);
    }

    public static User fromRequest(String email, String username, String password) {
        return new User(email, username, password);
    }

    public User createWithId(UUID id) {
        return new User(id, email, username, password);
    }
}
