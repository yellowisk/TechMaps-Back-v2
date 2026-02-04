package com.acing.techmaps.web.model.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationDTO(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6, max = 32) String password
) {
}
