package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.group.GroupRoadmapCRUD;
import com.acing.techmaps.web.model.group.request.GroupRoadmapRequest;
import com.acing.techmaps.web.model.group.response.GroupRoadmapResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/group-roadmaps")
public class GroupRoadmapController {
    private final GroupRoadmapCRUD groupRoadmapCRUD;

    public GroupRoadmapController(GroupRoadmapCRUD groupRoadmapCRUD) {
        this.groupRoadmapCRUD = groupRoadmapCRUD;
    }

    @PostMapping("")
    public ResponseEntity<GroupRoadmapResponse> createGroupRoadmap(
            @RequestBody GroupRoadmapRequest request) {
        return ResponseEntity.ok(GroupRoadmapResponse.fromGroupRoadmap(groupRoadmapCRUD.create(request)));
    }

    @GetMapping("/id/{groupRoadmapId}")
    public ResponseEntity<GroupRoadmapResponse> getGroupRoadmapById(
            @PathVariable UUID groupRoadmapId) {
        return ResponseEntity.ok(GroupRoadmapResponse.fromGroupRoadmap(groupRoadmapCRUD.getById(groupRoadmapId)));
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<List<GroupRoadmapResponse>> getGroupRoadmapByGroupId(
            @PathVariable UUID groupId) {
        return ResponseEntity.ok(groupRoadmapCRUD.getByGroupId(groupId)
                .stream().map(GroupRoadmapResponse::fromGroupRoadmap).toList());
    }

    @GetMapping("/roadmaps/{roadmapId}")
    public ResponseEntity<List<GroupRoadmapResponse>> getGroupRoadmapByRoadmapId(
            @PathVariable UUID roadmapId) {
        return ResponseEntity.ok(groupRoadmapCRUD.getByRoadmapId(roadmapId)
                .stream().map(GroupRoadmapResponse::fromGroupRoadmap).toList());
    }

    @DeleteMapping("/{groupRoadmapId}")
    public ResponseEntity<Void> deleteGroupRoadmap(
            @PathVariable UUID groupRoadmapId) {
        groupRoadmapCRUD.delete(groupRoadmapId);
        return ResponseEntity.noContent().build();
    }

}
