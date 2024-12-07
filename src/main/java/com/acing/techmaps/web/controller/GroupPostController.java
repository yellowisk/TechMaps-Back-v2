package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.group.GroupPostCRUD;
import com.acing.techmaps.web.model.group.request.GroupPostRequest;
import com.acing.techmaps.web.model.group.response.GroupPostResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v2/group-posts")
@AllArgsConstructor
public class GroupPostController {
    private final GroupPostCRUD groupPostCRUD;

    @PostMapping("")
    public ResponseEntity<GroupPostResponse> createGroupPost(@RequestBody GroupPostRequest request) {
        return ResponseEntity.ok(GroupPostResponse.fromGroupPost(groupPostCRUD.create(request)));
    }

    @GetMapping("/id/{groupPostId}")
    public ResponseEntity<GroupPostResponse> getGroupPostById(@PathVariable UUID groupPostId) {
        return ResponseEntity.ok(GroupPostResponse.fromGroupPost(groupPostCRUD.getById(groupPostId)));
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<List<GroupPostResponse>> getGroupPostByGroupId(@PathVariable UUID groupId) {
        return ResponseEntity.ok(groupPostCRUD.getByGroupId(groupId).stream().map(GroupPostResponse::fromGroupPost).toList());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<GroupPostResponse>> getGroupPostByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(groupPostCRUD.getByUserId(userId).stream().map(GroupPostResponse::fromGroupPost).toList());
    }

    @PatchMapping("/{groupPostId}")
    public ResponseEntity<GroupPostResponse> updateGroupPost(@PathVariable UUID groupPostId,
                                                             @RequestBody GroupPostRequest request) {
        return ResponseEntity.ok(GroupPostResponse.fromGroupPost(groupPostCRUD.updateGroupPost(request, groupPostId)));
    }

    @DeleteMapping("/{groupPostId}")
    public ResponseEntity<Void> deleteGroupPost(@PathVariable UUID groupPostId) {
        groupPostCRUD.delete(groupPostId);
        return ResponseEntity.noContent().build();
    }

}
