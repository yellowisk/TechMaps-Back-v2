package com.acing.techmaps.web.model.group.request;

import com.acing.techmaps.domain.entities.group.GroupComment;

import java.util.UUID;

public record GroupCommentRequest(UUID groupPostId, UUID groupUserId, String text) {

    public GroupComment toGroupComment() {
        return GroupComment.fromRequest(groupPostId, groupUserId, text);
    }
}
