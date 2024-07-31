package com.acing.techmaps.usecases.stage_user.gateway;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.List;
import java.util.UUID;

public interface Stage_UserDAO {
    StageUser add(StageUser stageUser);
    StageUser findById(UUID id);
    StageUser findByUserId(UUID userId);
    List<StageUser> findByStageId(UUID stageId);
    StageUser update(StageUser stageUser);
    void delete(StageUser stageUser);
}
