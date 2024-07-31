package com.acing.techmaps.usecases.stage_user.gateway;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.List;
import java.util.UUID;

public interface Stage_UserDAO {
    StageUser add(StageUser stage_user);
    StageUser findById(UUID id);
    StageUser findByStageId(UUID stageId);
    List<StageUser> findByUserId(UUID userId);
    StageUser update(StageUser stage_user);
    void delete(StageUser stage_user);
}
