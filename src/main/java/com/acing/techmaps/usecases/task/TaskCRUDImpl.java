package com.acing.techmaps.usecases.task;

import com.acing.techmaps.domain.entities.task.Task;
import com.acing.techmaps.usecases.task.gateway.TaskDAO;
import com.acing.techmaps.web.model.task.request.TaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class TaskCRUDImpl  implements TaskCRUD{

    private final TaskDAO taskDAO;

    public TaskCRUDImpl(TaskDAO taskDAO) {

        this.taskDAO = taskDAO;
    }

    @Override
    public Task create(TaskRequest request) {
        return taskDAO.add(request.toTask());
    }

    @Override
    public Task getById(UUID id) {
        return taskDAO.findById(id);
    }

    @Override
    public List<Task> getByTask(UUID task) {
        return taskDAO.findByTask(task);
    }

    @Override
    public Task update(UUID id, String name) {
        Task task = taskDAO.findById(id);
        task.setName(name);
        return taskDAO.update(task);
    }

    @Override
    public void delete(UUID id) {
        Task task = taskDAO.findById(id);
        taskDAO.delete(task);
    }
}
