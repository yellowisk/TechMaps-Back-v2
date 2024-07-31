package com.acing.techmaps.usecases.stage_user;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;
import java.util.List;
import java.util.UUID;

public interface Stage_UserCRUD {
    StageUser create(StageUserRequest request);
    StageUser getById(UUID id);
    StageUser getByUserId( UUID userId);
    List<StageUser> getByStageId(UUID stageId);
    StageUser update(UUID id, Boolean isCompleted);
    void delete(UUID id);
}
