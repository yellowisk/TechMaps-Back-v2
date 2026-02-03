package com.acing.techmaps.web.model.group.request;

import com.acing.techmaps.domain.entities.group.GroupComment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GroupCommentRequest(
        @NotNull UUID groupPostId,
        @NotNull UUID groupUserId,
        @NotBlank String text
) {

    public GroupComment toGroupComment() {
        return GroupComment.fromRequest(groupPostId, groupUserId, text);
    }
}