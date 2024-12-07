package com.acing.techmaps.web.model.group.response;

import com.acing.techmaps.domain.entities.group.GroupComment;

import java.util.UUID;

public record GroupCommentResponse(UUID id, UUID groupPostId, UUID userId, String text) {

    public static GroupCommentResponse fromGroupComment(GroupComment groupComment) {
        return new GroupCommentResponse(
                groupComment.getId(),
                groupComment.getGroupPostId(),
                groupComment.getUserId(),
                groupComment.getText()
        );
    }
}
