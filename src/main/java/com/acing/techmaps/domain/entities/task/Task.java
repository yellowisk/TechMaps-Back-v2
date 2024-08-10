package com.acing.techmaps.domain.entities.task;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Task {
    private UUID id;
    private UUID stageId;
    private String title;
    private String description;
    private int position;

    public Task(UUID id, UUID stageId, String title, String description, int position) {
        this.id = id;
        this.stageId = stageId;
        this.title = title;
        this.description = description;
        this.position = position;
    }

    public Task(UUID stageId, String title, String description, int position) {
        this.stageId = stageId;
        this.title = title;
        this.description = description;
        this.position = position;
    }

    public static Task createFull(UUID id, UUID stageId, String title, String description, int position) {
        return new Task(id, stageId, title, description, position);
    }

    public static Task fromRequest(UUID stageId, String title, String description, int position) {
        return new Task(stageId, title, description, position);
    }

}
