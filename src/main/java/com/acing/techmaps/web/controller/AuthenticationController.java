package com.acing.techmaps.web.controller;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.security.SecurityFilter;
import com.acing.techmaps.security.service.TokenService;
import com.acing.techmaps.usecases.user.UserCRUD;
import com.acing.techmaps.web.model.user.request.AuthenticationDTO;
import com.acing.techmaps.web.model.user.request.UserRequest;
import com.acing.techmaps.web.model.user.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private UserCRUD userCRUD;
    private TokenService tokenService;
    private SecurityFilter securityFilter;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody AuthenticationDTO request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        User user = userCRUD.getByEmail(((User) auth.getPrincipal()).getEmail());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return ResponseEntity.ok().headers(headers).body(UserResponse.createFromUser(user));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request) {
        User user = userCRUD.registerNewUser(request);
        return login(new AuthenticationDTO(user.getEmail(), request.password()));
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<UserResponse> refreshToken(@RequestHeader("Authorization") String token) {
        String newToken = tokenService.refreshToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", newToken);
        return ResponseEntity.ok().headers(headers).build();
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        securityFilter.eraseCredentials(request);
        return ResponseEntity.noContent().build();
    }

}
