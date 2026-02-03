package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.invite.SystemInviteCRUD;
import com.acing.techmaps.web.model.invite.request.SystemInviteRequest;
import com.acing.techmaps.web.model.invite.response.SystemInviteResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/system-invites")
public class SystemInviteController {
    private final SystemInviteCRUD systemInviteCRUD;

    @PostMapping("")
    public ResponseEntity<SystemInviteResponse> createSystemInvite(@RequestBody @Valid SystemInviteRequest request) {
        return ResponseEntity.ok(SystemInviteResponse.fromSystemInvite(systemInviteCRUD.create(request)));
    }

    @GetMapping("")
    public ResponseEntity<List<SystemInviteResponse>> getAllSystemInvites() {
        List<SystemInviteResponse> invites = systemInviteCRUD.getAll().stream()
                .map(SystemInviteResponse::fromSystemInvite)
                .toList();
        return ResponseEntity.ok(invites);
    }

    @GetMapping("/id/{systemId}")
    public ResponseEntity<SystemInviteResponse> geSystemInviteById(@PathVariable UUID systemId) {
        return ResponseEntity.ok(SystemInviteResponse.fromSystemInvite(systemInviteCRUD.getById(systemId)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<SystemInviteResponse> geSystemInviteByEmail(@PathVariable String email) {
        return ResponseEntity.ok(SystemInviteResponse.fromSystemInvite(systemInviteCRUD.getByEmail(email)));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<SystemInviteResponse> getSystemInviteByCode(@PathVariable String code) {
        return ResponseEntity.ok(SystemInviteResponse.fromSystemInvite(systemInviteCRUD.getByCode(code)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{systemId}")
    public ResponseEntity<Void> deleteSystemInvite(@PathVariable UUID systemId) {
        systemInviteCRUD.delete(systemId);
        return ResponseEntity.noContent().build();
    }

}
