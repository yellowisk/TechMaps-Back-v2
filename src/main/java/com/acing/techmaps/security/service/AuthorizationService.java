package com.acing.techmaps.security.service;

import com.acing.techmaps.usecases.user.UserCRUD;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private UserCRUD userCRUD;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userCRUD.getByEmail(email);
    }}
