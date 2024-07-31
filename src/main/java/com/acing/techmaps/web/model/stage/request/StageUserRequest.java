package com.acing.techmaps.web.model.stage.request;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.UUID;

public record StageUserRequest(UUID stageId, UUID userId) {
    public StageUserRequest(UUID stageId, UUID userId) {
        this.stageId = stageId;
        this.userId = userId;

    }

    public StageUser toStageUser() {
        return StageUser.fromRequest(stageId, userId);
    }
}

