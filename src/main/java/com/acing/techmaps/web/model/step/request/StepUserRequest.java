package com.acing.techmaps.web.model.step.request;

import com.acing.techmaps.domain.entities.step.StepUser;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StepUserRequest(@NotNull UUID stepId, @NotNull UUID roadmapUserId, boolean isDone) {
    public StepUser toStepUser() {
        return new StepUser(stepId, roadmapUserId, isDone);
    }
}