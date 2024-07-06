package com.acing.techmaps.usecases.stage;

import com.acing.techmaps.domain.entities.stage.Stage;
import com.acing.techmaps.web.model.stage.request.StageRequest;

import java.util.List;
import java.util.UUID;

public interface StageCRUD {
    Stage create(StageRequest request);
    Stage getById(UUID id);
    List<Stage> getByRoadmapId(UUID roadmapId);
    Stage update(UUID id, String name);
    void delete(UUID id);
}
