package com.acing.techmaps.usecases.stage;

import com.acing.techmaps.domain.entities.stage.Stage;
import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;

import java.util.List;
import java.util.UUID;

public interface StageUserCRUD {
    StageUser create(StageUserRequest request);
    StageUser getById(UUID id);
    StageUser getByStageId(UUID stageId);
    List<StageUser> getByRoadmapUserId(UUID roadmapUserId);
    StageUser update(boolean isDone, int position, UUID id);

    void delete(UUID id);
}
