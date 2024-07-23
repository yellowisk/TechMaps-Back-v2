package com.acing.techmaps.web.model.task.request;

import com.acing.techmaps.domain.entities.task.TaskUser;

import java.util.UUID;

public record TaskUserRequest(UUID taskId, UUID roadmapUserId) {
    public TaskUserRequest(UUID taskId, UUID roadmapUserId) {
        this.taskId = taskId;
        this.roadmapUserId = roadmapUserId;
    }

    public TaskUser toTaskUser() {
        return TaskUser.fromRequest(taskId, roadmapUserId);
    }
}
