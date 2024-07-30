package com.acing.techmaps.usecases.step;

import com.acing.techmaps.domain.entities.step.StepUser;
import com.acing.techmaps.web.model.step.request.StepUserRequest;

import java.util.List;
import java.util.UUID;

public interface StepUserCRUD {
    StepUser create(StepUserRequest stepUserRequest);
    StepUser getById(UUID id);
    List<StepUser> getByRoadmapUserId(UUID roadmapUserId);
    List<StepUser> getByStepId(UUID stepId);
    StepUser updateStatus(boolean isDone, UUID id);
    void delete(UUID id);
}
