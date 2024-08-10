package com.acing.techmaps.usecases.task;

import com.acing.techmaps.domain.entities.task.Task;
import com.acing.techmaps.web.model.task.request.TaskRequest;
import com.acing.techmaps.web.model.task.request.TaskUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface TaskCRUD {
    Task create(TaskRequest taskRequest);
    Task getById(UUID id);
    List<Task> getByStageId(UUID stageId);
    Task update(TaskUpdateRequest taskUpdateRequest, UUID id);
    void delete(UUID id);
}
