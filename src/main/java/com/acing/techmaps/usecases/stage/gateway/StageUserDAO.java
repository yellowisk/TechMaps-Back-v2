package com.acing.techmaps.usecases.stage.gateway;

import com.acing.techmaps.domain.entities.stage.StageUser;

import java.util.List;
import java.util.UUID;

public interface StageUserDAO {
    StageUser add(StageUser stageUser);
    StageUser findById(UUID id);
    List<StageUser> findByStageId(UUID stageId);
    List<StageUser> findByUserId(UUID userId);
    StageUser update(StageUser stageUser);
    void delete(StageUser stageUser);
}
