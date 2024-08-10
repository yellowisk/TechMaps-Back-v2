package com.acing.techmaps.web.model.stage.request;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.UUID;

public record StageUserRequest(UUID stageId, UUID roadmapUserId, boolean isDone, int position) {
    public StageUser toStageUser() {
        return StageUser.fromRequest(stageId, roadmapUserId, isDone, position);
    }
}
