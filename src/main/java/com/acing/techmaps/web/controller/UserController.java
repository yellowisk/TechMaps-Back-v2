package com.acing.techmaps.web.controller;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.usecases.user.UserCRUD;
import com.acing.techmaps.web.model.user.request.UserRequest;
import com.acing.techmaps.web.model.user.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserCRUD userCRUD;

    public UserController(UserCRUD userCRUD) {
        this.userCRUD = userCRUD;
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID userId) {
        User user = userCRUD.getById(userId);
        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        User user = userCRUD.getByEmail(email);
        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        User user = userCRUD.getByUsername(username);
        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }

    @GetMapping("/position/{position}")
    public ResponseEntity<UserResponse> getUserByPosition(@PathVariable String position) {
        User user = userCRUD.getByPosition(position);
        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserRequest userRequest,
                                                   @PathVariable UUID userId) {
        User user = userCRUD.update(userRequest, userId);
        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{userId}/position/{position}")
    public ResponseEntity<UserResponse> updateUserPosition(@PathVariable String position,
                                                           @PathVariable UUID userId) {
        User user = userCRUD.updatePosition(position, userId);
        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userCRUD.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
