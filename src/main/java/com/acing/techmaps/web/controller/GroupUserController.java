package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.group.GroupUserCRUD;
import com.acing.techmaps.web.model.group.request.GroupUserRequest;
import com.acing.techmaps.web.model.group.response.GroupUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/group-users")
public class GroupUserController {

    private final GroupUserCRUD groupUserCRUD;

    public GroupUserController(GroupUserCRUD groupUserCRUD) {
        this.groupUserCRUD = groupUserCRUD;
    }

    @GetMapping("/id/{groupUserId}")
    public ResponseEntity<GroupUserResponse> getGroupUserById(@PathVariable  UUID groupUserId) {
        return ResponseEntity.ok(GroupUserResponse.fromGroupUser(groupUserCRUD.getById(groupUserId)));
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<List<GroupUserResponse>> getGroupUserByGroupId(@PathVariable UUID groupId) {
        return ResponseEntity.ok(groupUserCRUD.getByGroupId(groupId)
                .stream().map(GroupUserResponse::fromGroupUser).toList());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<GroupUserResponse>> getGroupUserByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(groupUserCRUD.getByUserId(userId)
                .stream().map(GroupUserResponse::fromGroupUser).toList());
    }

    @PostMapping("")
    public ResponseEntity<GroupUserResponse> createGroupUser(
            @RequestBody GroupUserRequest request) {
        return ResponseEntity.ok(GroupUserResponse.fromGroupUser(groupUserCRUD.create(request)));
    }

    @PatchMapping("/{groupUserId}/role/{role}")
    public ResponseEntity<GroupUserResponse> updateGroupUserRole(
            @PathVariable UUID groupUserId, @PathVariable String role) {
        return ResponseEntity.ok(GroupUserResponse.fromGroupUser(groupUserCRUD.updateRole(groupUserId, role)));
    }

    @DeleteMapping("/{groupUserId}")
    public ResponseEntity<Void> deleteGroupUser(@PathVariable UUID groupUserId) {
        groupUserCRUD.delete(groupUserId);
        return ResponseEntity.noContent().build();
    }

}
