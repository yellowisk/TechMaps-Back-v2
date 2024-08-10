package com.acing.techmaps.web.model.stage.response;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.UUID;

public record StageUserResponse(UUID id, UUID stageId, UUID roadmapUserId, boolean isDone, int position) {

        public static StageUserResponse createFromStageUser(StageUser stageUser) {
            return new StageUserResponse(
                stageUser.getId(),
                stageUser.getStageId(),
                stageUser.getRoadmapUserId(),
                stageUser.isDone(),
                stageUser.getPosition()
            );
        }
}
