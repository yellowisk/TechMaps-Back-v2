package com.acing.techmaps.web.model.stage.response;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.UUID;

<<<<<<< HEAD
public record StageUserResponse(UUID stageId, UUID userId, Boolean isCompleted) {
=======
public record StageUserResponse(UUID stageId, UUID userId, boolean isCompleted) {
    public StageUserResponse(UUID stageId, UUID userId, boolean isCompleted) {
        this.stageId = stageId;
        this.userId = userId;
        this.isCompleted = isCompleted;
    }
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc

    public static StageUserResponse createFromStageUser(StageUser stageUser) {
        return new StageUserResponse(
                stageUser.getStageId(),
                stageUser.getUserId(),
<<<<<<< HEAD
                stageUser.getIsCompleted()
=======
                stageUser.isCompleted()
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
        );
    }

}

