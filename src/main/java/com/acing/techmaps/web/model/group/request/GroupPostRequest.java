package com.acing.techmaps.web.model.group.request;

import com.acing.techmaps.domain.entities.group.GroupPost;

import java.util.UUID;

public record GroupPostRequest(UUID groupId, UUID groupUserId, String title, String text) {
    public GroupPost toGroupPost() {
        return GroupPost.fromRequest(groupId, groupUserId, title, text);
    }

}
