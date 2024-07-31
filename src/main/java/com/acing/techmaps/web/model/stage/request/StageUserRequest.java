package com.acing.techmaps.web.model.stage.request;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.UUID;

public record StageUserRequest(UUID userId, UUID stageId) {
    public StageUserRequest(UUID userId, UUID stageId) {
        this.stageId = stageId;
        this.userId = userId;

    }

    public StageUser toStageUser() {
        return StageUser.fromRequest(userId, stageId);
    }
}

