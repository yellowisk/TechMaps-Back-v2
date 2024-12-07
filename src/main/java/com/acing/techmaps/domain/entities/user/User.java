package com.acing.techmaps.domain.entities.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
public class User implements UserDetails {
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

    public User(String email) {
        this.email = email;
    }

    public static User createWithEmail(String email) {
        return new User(email);
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
