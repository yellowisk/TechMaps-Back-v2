package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.group.GroupCommentCRUD;
import com.acing.techmaps.web.model.group.request.GroupCommentRequest;
import com.acing.techmaps.web.model.group.response.GroupCommentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/group-comments")
@AllArgsConstructor
public class GroupCommentController {
    private final GroupCommentCRUD groupCommentCRUD;

    @PostMapping("")
    public ResponseEntity<GroupCommentResponse> createGroupComment(@RequestBody @Valid GroupCommentRequest request) {
        return ResponseEntity.ok(GroupCommentResponse.fromGroupComment(groupCommentCRUD.create(request)));
    }

    @GetMapping("/id/{groupCommentId}")
    public ResponseEntity<GroupCommentResponse> getGroupCommentById(@PathVariable UUID groupCommentId) {
        return ResponseEntity.ok(GroupCommentResponse.fromGroupComment(groupCommentCRUD.getById(groupCommentId)));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<GroupCommentResponse>> getGroupCommentByPostId(@PathVariable UUID postId) {
        return ResponseEntity.ok(groupCommentCRUD.getByGroupPostId(postId).stream().map((GroupCommentResponse::fromGroupComment)).toList());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<GroupCommentResponse>> getGroupCommentByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(groupCommentCRUD.getByUserId(userId).stream().map(GroupCommentResponse::fromGroupComment).toList());
    }

    @PatchMapping("/{groupCommentId}")
    public ResponseEntity<GroupCommentResponse> updateGroupComment(@PathVariable UUID groupCommentId,
                                                                  @RequestBody @Valid GroupCommentRequest request) {
        return ResponseEntity.ok(GroupCommentResponse.fromGroupComment(groupCommentCRUD.updateComment(request, groupCommentId)));
    }

    @DeleteMapping("/{groupCommentId}")
    public ResponseEntity<Void> deleteGroupComment(@PathVariable UUID groupCommentId) {
        groupCommentCRUD.delete(groupCommentId);
        return ResponseEntity.noContent().build();
    }
}
