package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.group.GroupCRUD;
import com.acing.techmaps.web.model.group.request.GroupRequest;
import com.acing.techmaps.web.model.group.response.GroupResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/groups")
public class GroupController {
    private final GroupCRUD groupCRUD;

    public GroupController(GroupCRUD groupCRUD) {
        this.groupCRUD = groupCRUD;
    }

    @PreAuthorize("hasRole('EDUCATIONAL')")
    @PostMapping("")
    public ResponseEntity<GroupResponse> createGroup(@RequestBody @Valid GroupRequest request) {
        return ResponseEntity.ok(GroupResponse.fromGroup(groupCRUD.create(request)));
    }

    @GetMapping("/id/{groupId}")
    public ResponseEntity<GroupResponse> getGroupById(@PathVariable UUID groupId) {
        return ResponseEntity.ok(GroupResponse.fromGroup(groupCRUD.getById(groupId)));
    }

    @GetMapping("/name/{groupName}")
    public ResponseEntity<GroupResponse> getGroupByName(@PathVariable String groupName) {
        return ResponseEntity.ok(GroupResponse.fromGroup(groupCRUD.getByName(groupName)));
    }

    @GetMapping("/root/{groupId}")
    public ResponseEntity<GroupResponse> getRootGroup(@PathVariable UUID groupId) {
        return ResponseEntity.ok(GroupResponse.fromGroup(groupCRUD.findRoot(groupId)));
    }

    @GetMapping("/hierarchy/{groupId}")
    public ResponseEntity<List<GroupResponse>> getGroupHierarchy(@PathVariable UUID groupId) {
        List<GroupResponse> groups = groupCRUD.findGroupHierarchy(groupId).stream()
                .map(GroupResponse::fromGroup)
                .toList();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<GroupResponse>> getGroupsByCreatorId(@PathVariable UUID creatorId) {
        List<GroupResponse> groups = groupCRUD.getByCreatorId(creatorId).stream()
                .map(GroupResponse::fromGroup)
                .toList();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<GroupResponse>> getGroupsByParentId(@PathVariable UUID parentId) {
        List<GroupResponse> groups = groupCRUD.getByParentId(parentId).stream()
                .map(GroupResponse::fromGroup)
                .toList();
        return ResponseEntity.ok(groups);
    }

    @PatchMapping("/{groupId}")
    public ResponseEntity<GroupResponse> updateGroup(@PathVariable UUID groupId,
                                                      @RequestBody @Valid GroupRequest request) {
        return ResponseEntity.ok(GroupResponse.fromGroup(groupCRUD.updateGroup(request, groupId)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable UUID groupId) {
        groupCRUD.delete(groupId);
        return ResponseEntity.noContent().build();
    }

}
