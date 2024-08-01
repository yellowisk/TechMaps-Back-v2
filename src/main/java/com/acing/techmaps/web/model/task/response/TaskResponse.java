package com.acing.techmaps.web.model.task.response;

import com.acing.techmaps.domain.entities.task.Task;

import java.util.UUID;

public record TaskResponse(UUID id,UUID task, String name) {
    public TaskResponse(UUID id, UUID task, String name) {
        this.id = id;
        this.task = task;
        this.name = name;
    }

    public static TaskResponse fromTask(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTask(),
                task.getName()
        );
    }
}
<<<<<<< HEAD
//só para aparecer
=======
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
