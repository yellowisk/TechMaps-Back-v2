package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.invite.GroupInviteCRUD;
import com.acing.techmaps.web.model.invite.request.GroupInviteRequest;
import com.acing.techmaps.web.model.invite.response.GroupInviteResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/group-invites")
public class GroupInviteController {
    private final GroupInviteCRUD groupInviteCRUD;

    @PostMapping("")
    public ResponseEntity<GroupInviteResponse> createGroupInvite(@RequestBody GroupInviteRequest request) {
        return ResponseEntity.ok(GroupInviteResponse.fromGroupInvite(groupInviteCRUD.create(request)));
    }

    @GetMapping("")
    public ResponseEntity<List<GroupInviteResponse>> getAllGroupInvites() {
        List<GroupInviteResponse> invites = groupInviteCRUD.getAll().stream()
                .map(GroupInviteResponse::fromGroupInvite)
                .toList();
        return ResponseEntity.ok(invites);
    }

    @GetMapping("/id/{groupId}")
    public ResponseEntity<GroupInviteResponse> getGroupInviteById(@PathVariable UUID groupId) {
        return ResponseEntity.ok(GroupInviteResponse.fromGroupInvite(groupInviteCRUD.getById(groupId)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GroupInviteResponse> getGroupInviteByEmail(@PathVariable String email) {
        return ResponseEntity.ok(GroupInviteResponse.fromGroupInvite(groupInviteCRUD.getByEmail(email)));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<GroupInviteResponse> getGroupInviteByCode(@PathVariable String code) {
        return ResponseEntity.ok(GroupInviteResponse.fromGroupInvite(groupInviteCRUD.getByCode(code)));
    }

    @PreAuthorize("hasRole('EDUCATIONAL')")
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroupInvite(@PathVariable UUID groupId) {
        groupInviteCRUD.delete(groupId);
        return ResponseEntity.noContent().build();
    }

}
