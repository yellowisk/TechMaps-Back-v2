package com.acing.techmaps.web.model.task.request;

import com.acing.techmaps.domain.entities.task.Task;

import java.util.UUID;

public record TaskRequest(UUID task, String name) {
    public TaskRequest(UUID task, String name) {
        this.task = task;
        this.name = name;
    }
    public Task toTask() {
        return Task.fromRequest(task,name);
    }
}
