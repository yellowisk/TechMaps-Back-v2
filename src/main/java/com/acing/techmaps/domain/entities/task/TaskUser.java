package com.acing.techmaps.domain.entities.task;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TaskUser {
    private UUID id;
    private UUID taskId;
    private UUID roadmapUserId;
    private Boolean isDone;

    public TaskUser(UUID id, UUID taskId, UUID roadmapUserId, Boolean isDone) {
        this.id = id;
        this.taskId = taskId;
        this.roadmapUserId = roadmapUserId;
        this.isDone = isDone;
    }

    public TaskUser(UUID taskId, UUID roadmapUserId) {
        this.taskId = taskId;
        this.roadmapUserId = roadmapUserId;
    }

    public static TaskUser createFull(UUID id, UUID taskId, UUID roadmapUserId, Boolean isDone) {
        return new TaskUser(id, taskId, roadmapUserId, isDone);
    }

    public static TaskUser fromRequest(UUID taskId, UUID roadmapUserId) {
        return new TaskUser(taskId, roadmapUserId);
    }
}
