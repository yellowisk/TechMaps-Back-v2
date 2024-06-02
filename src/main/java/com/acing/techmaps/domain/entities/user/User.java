package com.acing.techmaps.domain.entities.user;

import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
