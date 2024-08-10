package com.acing.techmaps.usecases.task;

import com.acing.techmaps.domain.entities.task.Task;
import com.acing.techmaps.usecases.task.gateway.TaskDAO;
import com.acing.techmaps.web.model.task.request.TaskRequest;
import com.acing.techmaps.web.model.task.request.TaskUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskCRUDImpl implements TaskCRUD {
    private final TaskDAO taskDAO;

    @Override
    public Task create(TaskRequest taskRequest) {
        return taskDAO.add(taskRequest.toTask());
    }

    @Override
    public Task getById(UUID id) {
        return taskDAO.findById(id);
    }

    @Override
    public List<Task> getByStageId(UUID stageId) {
        return taskDAO.findByStage(stageId);
    }

    @Override
    public Task update(TaskUpdateRequest taskUpdateRequest, UUID id) {
        Task task = taskDAO.findById(id);
        task.setTitle(taskUpdateRequest.title());
        task.setDescription(taskUpdateRequest.description());
        task.setPosition(taskUpdateRequest.position());
        return taskDAO.update(task);
    }

    @Override
    public void delete(UUID id) {
        Task task = taskDAO.findById(id);
        taskDAO.delete(task);
    }
}
