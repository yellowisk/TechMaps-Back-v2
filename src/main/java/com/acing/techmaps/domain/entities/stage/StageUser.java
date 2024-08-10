package com.acing.techmaps.domain.entities.stage;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StageUser {
    private UUID id;
    private UUID stageId;
    private UUID roadmapUserId;
    private boolean isDone;
    private int position;

    public StageUser(UUID id, UUID stageId, UUID roadmapUserId, boolean isDone, int position) {
        this.id = id;
        this.stageId = stageId;
        this.roadmapUserId = roadmapUserId;
        this.isDone = isDone;
        this.position = position;
    }

    public StageUser(UUID stageId, UUID roadmapUserId, boolean isDone, int position) {
        this.stageId = stageId;
        this.roadmapUserId = roadmapUserId;
        this.isDone = isDone;
        this.position = position;
    }

    public static StageUser createFull(UUID id, UUID stageId, UUID roadmapUserId, boolean isDone, int position) {
        return new StageUser(id, stageId, roadmapUserId, isDone, position);
    }

    public static StageUser fromRequest(UUID stageId, UUID roadmapUserId, boolean isDone, int position) {
        return new StageUser(stageId, roadmapUserId, isDone, position);
    }

}
