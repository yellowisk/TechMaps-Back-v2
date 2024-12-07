package com.acing.techmaps.web.model.group.response;

import com.acing.techmaps.domain.entities.group.GroupPost;

import java.util.UUID;

public record GroupPostResponse(UUID id, UUID groupId, UUID userId, String title, String text) {

    public static GroupPostResponse fromGroupPost(GroupPost groupPost) {
        return new GroupPostResponse(
                groupPost.getId(),
                groupPost.getGroupId(),
                groupPost.getUserId(),
                groupPost.getTitle(),
                groupPost.getText()
        );
    }
}
