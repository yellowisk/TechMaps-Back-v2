package com.acing.techmaps.usecases.step.gateway;

import com.acing.techmaps.domain.entities.step.StepUser;

import java.util.List;
import java.util.UUID;

public interface StepUserDAO {
    StepUser add(StepUser stepUser);
    StepUser findById(UUID id);
    List<StepUser> findByRoadmapUserId(UUID roadmapUserId);
    List<StepUser> findByStepId(UUID stepId);
    StepUser updateStatus(StepUser stepUser);
    void delete(UUID id);
}
