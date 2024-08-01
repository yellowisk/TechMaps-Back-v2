package com.acing.techmaps.web.model.stage.request;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.UUID;

<<<<<<< HEAD
public record StageUserRequest(UUID userId, UUID stageId) {
    public StageUserRequest(UUID userId, UUID stageId) {
=======
public record StageUserRequest(UUID stageId, UUID userId) {
    public StageUserRequest(UUID stageId, UUID userId) {
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
        this.stageId = stageId;
        this.userId = userId;

    }

    public StageUser toStageUser() {
<<<<<<< HEAD
        return StageUser.fromRequest(userId, stageId);
=======
        return StageUser.fromRequest(stageId, userId);
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
    }
}

