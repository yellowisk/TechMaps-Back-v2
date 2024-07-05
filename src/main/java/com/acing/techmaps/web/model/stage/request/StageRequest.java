package com.acing.techmaps.web.model.stage.request;

import com.acing.techmaps.domain.entities.stage.Stage;

import java.util.UUID;

public record StageRequest(UUID roadmapId, String name) {
    public StageRequest(UUID roadmapId, String name) {
        this.roadmapId = roadmapId;
        this.name = name;
    }

    public Stage toStage(UUID roadmapId, String name) {
        return Stage.fromRequest(roadmapId, name);
    }

}
