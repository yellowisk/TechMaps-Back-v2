package com.acing.techmaps.web.model.task.response;

import com.acing.techmaps.domain.entities.task.TaskUser;

import java.util.UUID;

public record TaskUserResponse(UUID id, UUID taskId, UUID roadmapUserId, Boolean isDone) {
    public TaskUserResponse(UUID id, UUID taskId, UUID roadmapUserId, Boolean isDone) {
        this.id = id;
        this.taskId = taskId;
        this.roadmapUserId = roadmapUserId;
        this.isDone = isDone;
    }

    public static TaskUserResponse createFromTaskUser(TaskUser taskUser) {
        return new TaskUserResponse(
            taskUser.getId(),
            taskUser.getTaskId(),
            taskUser.getRoadmapUserId(),
            taskUser.getIsDone()
        );
    }
}
