package com.acing.techmaps.web.model.user.request;

import com.acing.techmaps.domain.entities.user.Position;
import com.acing.techmaps.domain.entities.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record UserRequest(
        @NotBlank @Email String email,
        @NotBlank String position,
        @NotBlank @Size(min = 2) String username,
        @NotBlank @Size(min = 6) String password
) {

    public User toUser() {
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        return User.fromRequest(email, username, Position.valueOf(position), encryptedPassword);
    }

}