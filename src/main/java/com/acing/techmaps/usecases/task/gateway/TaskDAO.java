package com.acing.techmaps.usecases.task.gateway;

import com.acing.techmaps.domain.entities.task.Task;

import java.util.List;
import java.util.UUID;

public interface TaskDAO {
    Task add(Task task);
    Task findById(UUID id);
    List<Task> findByTask(UUID task);
    Task update(Task task);
    void delete(Task task);
    Boolean taskExists(UUID id);
}
