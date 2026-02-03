package com.acing.techmaps.web.model.stage.request;

import com.acing.techmaps.domain.entities.stage.StageUser;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StageUserRequest(
        @NotNull UUID stageId,
        @NotNull UUID roadmapUserId,
        boolean isDone,
        @Min(0) int position
) {
    public StageUser toStageUser() {
        return StageUser.fromRequest(stageId, roadmapUserId, isDone, position);
    }
}
