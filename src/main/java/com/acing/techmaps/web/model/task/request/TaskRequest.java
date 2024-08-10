package com.acing.techmaps.web.model.task.request;

import com.acing.techmaps.domain.entities.task.Task;

import java.util.UUID;

public record TaskRequest(UUID stageId, String title, String description, int position) {

    public Task toTask() {
        return Task.fromRequest(stageId, title, description, position);
    }

}
