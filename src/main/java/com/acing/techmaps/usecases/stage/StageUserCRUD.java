package com.acing.techmaps.usecases.stage;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;

import java.util.List;
import java.util.UUID;

public interface StageUserCRUD {
    StageUser create(StageUserRequest request);
    StageUser getById(UUID id);
    List<StageUser> getByStageId(UUID stageId);
    List<StageUser> getByUserId(UUID stageId);
    StageUser update(UUID id, boolean isCompleted);
    void delete(UUID id);
}
