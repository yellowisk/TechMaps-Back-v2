package com.acing.techmaps.usecases.task;

import com.acing.techmaps.domain.entities.task.TaskUser;
import com.acing.techmaps.usecases.task.gateway.TaskUserDAO;
import com.acing.techmaps.web.model.task.request.TaskUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskUserCRUDImpl implements TaskUserCRUD {
    private final TaskUserDAO taskUserDAO;

    public TaskUserCRUDImpl(TaskUserDAO taskUserDAO) {
        this.taskUserDAO = taskUserDAO;
    }

    @Override
    public TaskUser create(TaskUserRequest request) {
        return taskUserDAO.add(request.toTaskUser());
    }

    @Override
    public TaskUser getById(UUID id) {
        return taskUserDAO.findById(id);
    }

    @Override
    public TaskUser getByTaskId(UUID taskId) {
        return taskUserDAO.findByTaskId(taskId);
    }

    @Override
    public List<TaskUser> getByRoadmapUserId(UUID roadmapUserId) {
        return taskUserDAO.findByRoadmapUserId(roadmapUserId);
    }

    @Override
    public TaskUser update(UUID id, Boolean isDone) {
        TaskUser taskUser = taskUserDAO.findById(id);
        taskUser.setIsDone(isDone);
        return taskUserDAO.update(taskUser);
    }

    @Override
    public void delete(UUID id) {
        TaskUser taskUser = taskUserDAO.findById(id);
        taskUserDAO.delete(taskUser);
    }
}
