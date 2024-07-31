package com.acing.techmaps.usecases.stage_user;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;

import java.util.UUID;

public interface Stage_UserCRUD {
    StageUser create(StageUserRequest request);
    StageUser getById(UUID id);
    StageUser getById(UUID stageId, UUID userId);
    StageUser getByStageId(UUID stageId);
    StageUser update(UUID id, boolean isCompleted);
    void delete(UUID id);
}
