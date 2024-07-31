package com.acing.techmaps.web.model.step.request;

import com.acing.techmaps.domain.entities.step.StepUser;

import java.util.UUID;

public record StepUserRequest(UUID stepId, UUID roadmapUserId, boolean isDone) {
    public StepUser toStepUser() {
        return new StepUser(stepId, roadmapUserId, isDone);
    }
}