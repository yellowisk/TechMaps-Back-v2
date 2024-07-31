package com.acing.techmaps.web.model.step.response;

import com.acing.techmaps.domain.entities.step.StepUser;

import java.util.UUID;

public record StepUserResponse(UUID id, UUID stepId, UUID roadmapUserId, boolean isDone) {
    public static StepUserResponse createFromStepUser(StepUser stepUser) {
        return new StepUserResponse(
                stepUser.getId(),
                stepUser.getStepId(),
                stepUser.getRoadmapUserId(),
                stepUser.isDone()
        );
    }
}
