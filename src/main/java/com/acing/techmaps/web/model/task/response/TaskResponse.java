package com.acing.techmaps.web.model.task.response;

import com.acing.techmaps.domain.entities.task.Task;

import java.util.UUID;

public record TaskResponse(UUID id, UUID stageId, String title, String description, int position) {

    public static TaskResponse createFromTask(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getStageId(),
            task.getTitle(),
            task.getDescription(),
            task.getPosition()
        );
    }
}
