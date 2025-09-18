package com.acing.techmaps.security.utils;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.usecases.user.gateway.UserDAO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class SecurityUtils {

    private final UserDAO userDAO;

    public SecurityUtils(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                return (User) principal;
            } else if (principal instanceof String) {
                return userDAO.findByUsername((String) principal);
            }
        }
        return null;
    }

    public List<String> getCurrentUserAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null && authentication.isAuthenticated())
                ? authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
                : List.of();
    }

    public boolean hasDestructivePermissions(UUID userID) {
        List<String> authorities = getCurrentUserAuthorities();
        User currentUser = getCurrentUser();
        return authorities.contains("ROLE_ADMIN") || (currentUser != null && currentUser.getId().equals(userID));
    }
}