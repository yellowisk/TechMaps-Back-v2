package com.acing.techmaps.web.controller;

import com.acing.techmaps.domain.entities.user.User;
import com.acing.techmaps.usecases.user.UserCRUD;
import com.acing.techmaps.web.model.user.request.UserRequest;
import com.acing.techmaps.web.model.user.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v2/users")
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

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest,
                                                   @PathVariable UUID userId) {
        User user = userCRUD.update(userRequest, userId);
        return ResponseEntity.ok(UserResponse.createFromUser(user));
    }
}
