package com.acing.techmaps.usecases.task;

import com.acing.techmaps.domain.entities.task.Task;
import com.acing.techmaps.web.model.task.request.TaskRequest;

import java.util.List;
import java.util.UUID;

public interface TaskCRUD {
    Task create(TaskRequest request);
    Task getById(UUID id);
    List<Task> getByTask(UUID task);
    Task update(UUID id, String name);
    void delete(UUID id);
}
