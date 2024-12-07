package com.acing.techmaps.domain.entities.group;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GroupComment {
    private UUID id;
    private UUID groupPostId;
    private UUID userId;
    private String text;

    public GroupComment(UUID id, UUID groupPostId, UUID userId, String text) {
        this.id = id;
        this.groupPostId = groupPostId;
        this.userId = userId;
        this.text = text;
    }

    public GroupComment(UUID groupPostId, UUID userId, String text) {
        this.groupPostId = groupPostId;
        this.userId = userId;
        this.text = text;
    }

    public static GroupComment createFull(UUID id, UUID groupPostId, UUID userId, String text) {
        return new GroupComment(id, groupPostId, userId, text);
    }

    public static GroupComment fromRequest(UUID groupPostId, UUID userId, String text) {
        return new GroupComment(groupPostId, userId, text);
    }
}
