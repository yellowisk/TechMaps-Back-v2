package com.acing.techmaps.security.utils;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.usecases.user.gateway.UserDAO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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
}