package com.acing.techmaps.usecases.task;

import com.acing.techmaps.domain.entities.task.TaskUser;
import com.acing.techmaps.web.model.task.request.TaskUserRequest;

import java.util.List;
import java.util.UUID;

public interface TaskUserCRUD {
    TaskUser create(TaskUserRequest request);
    TaskUser getById(UUID id);
    TaskUser getByTaskId(UUID taskId);
    List<TaskUser> getByRoadmapUserId(UUID roadmapUserId);
    TaskUser update(UUID id, Boolean isDone);
    void delete(UUID id);
}
