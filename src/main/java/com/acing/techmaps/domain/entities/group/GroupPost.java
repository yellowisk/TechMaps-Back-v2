package com.acing.techmaps.domain.entities.group;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GroupPost {
    private UUID id;
    private UUID groupId;
    private UUID userId;
    private String title;
    private String text;

    public GroupPost(UUID id, UUID groupId, UUID userId, String title, String text) {
        this.id = id;
        this.groupId = groupId;
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public GroupPost(UUID groupId, UUID userId, String title, String text) {
        this.groupId = groupId;
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public static GroupPost createFull(UUID id, UUID groupId, UUID userId, String title, String text) {
        return new GroupPost(id, groupId, userId, title, text);
    }

    public static GroupPost fromRequest(UUID groupId, UUID userId, String title, String text) {
        return new GroupPost(groupId, userId, title, text);
    }
}
