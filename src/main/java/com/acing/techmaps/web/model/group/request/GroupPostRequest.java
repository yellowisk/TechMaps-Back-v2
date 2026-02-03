package com.acing.techmaps.web.model.group.request;

import com.acing.techmaps.domain.entities.group.GroupPost;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record GroupPostRequest(
        @NotNull UUID groupId,
        @NotNull UUID groupUserId,
        @NotBlank String title,
        @NotBlank String text
) {
    public GroupPost toGroupPost() {
        return GroupPost.fromRequest(groupId, groupUserId, title, text);
    }

}
