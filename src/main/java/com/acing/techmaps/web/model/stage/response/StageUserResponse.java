package com.acing.techmaps.web.model.stage.response;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.UUID;

public record StageUserResponse(UUID stageId, UUID userId, boolean isCompleted) {
    public StageUserResponse(UUID stageId, UUID userId, boolean isCompleted) {
        this.stageId = stageId;
        this.userId = userId;
        this.isCompleted = isCompleted;
    }

    public static StageUserResponse createFromStageUser(StageUser stageUser) {
        return new StageUserResponse(
                stageUser.getStageId(),
                stageUser.getUserId(),
                stageUser.isCompleted()
        );
    }

}

