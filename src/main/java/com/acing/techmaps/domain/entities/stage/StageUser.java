package com.acing.techmaps.domain.entities.stage;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StageUser {
    private UUID id;
<<<<<<< HEAD
    private UUID userId;
    private UUID stageId;
    private Boolean isCompleted;
=======
    private UUID stageId;
    private UUID userId;
    private boolean isCompleted;
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc

    public StageUser(UUID id, UUID stageId, UUID userId, boolean isCompleted) {
        this.id = id;
        this.stageId = stageId;
        this.userId = userId;
        this.isCompleted = isCompleted;
    }

    public StageUser(UUID stageId, UUID userId) {
        this.stageId = stageId;
        this.userId = userId;
    }

    public static StageUser createFull(UUID id, UUID stageId, UUID userId, boolean isCompleted) {
<<<<<<< HEAD
        return new StageUser(id, userId, stageId, isCompleted);
=======
        return new StageUser(id, stageId, userId, isCompleted);
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
    }
    public static StageUser fromRequest(UUID stageId, UUID userId) {
        return new StageUser(stageId, userId);
    }
}