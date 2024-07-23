package com.acing.techmaps.usecases.task.gateway;

import com.acing.techmaps.domain.entities.task.TaskUser;

import java.util.List;
import java.util.UUID;

public interface TaskUserDAO {
    TaskUser add(TaskUser taskUser);
    TaskUser findById(UUID id);
    TaskUser findByTaskId(UUID taskId);
    List<TaskUser> findByRoadmapUserId(UUID roadmapUserId);
    TaskUser update(TaskUser taskUser);
    void delete(TaskUser taskUser);
}
