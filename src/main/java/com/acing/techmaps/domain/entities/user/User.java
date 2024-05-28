package com.acing.techmaps.domain.entities.user;

import java.util.*;

public class User {
    private UUID id;
    private String email;
    private String name;
    private String password;

    public User(UUID id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static User createFromUser(String email, String name, String password) {
        return new User(email, name, password);
    }

    public User createWithId(UUID id) {
        return new User(id, email, name, password);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
