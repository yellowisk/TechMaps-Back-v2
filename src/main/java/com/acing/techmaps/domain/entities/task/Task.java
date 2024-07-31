package com.acing.techmaps.domain.entities.task;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Task {
    private UUID id;
    private UUID task;
    private String name;

    public Task(UUID id, UUID task, String name) {
        this.id = id;
        this.task = task;
        this.name = name;
    }

    public Task(UUID task, String name) {
        this.task = task;
        this.name = name;
    }

    public static Task fromRequest(UUID task, String name) {
        return new Task(task,name);
    }
    public static Task createFull(UUID id, UUID task, String name) {
        return new Task(id, task, name);
    }
}
