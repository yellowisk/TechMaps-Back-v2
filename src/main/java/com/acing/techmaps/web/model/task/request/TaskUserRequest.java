package com.acing.techmaps.web.model.task.request;

import com.acing.techmaps.domain.entities.task.TaskUser;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TaskUserRequest(@NotNull UUID taskId, @NotNull UUID roadmapUserId) {

    public TaskUser toTaskUser() {
        return TaskUser.fromRequest(taskId, roadmapUserId);
    }
}