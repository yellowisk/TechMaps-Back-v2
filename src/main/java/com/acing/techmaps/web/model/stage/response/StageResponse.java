package com.acing.techmaps.web.model.stage.response;

import com.acing.techmaps.domain.entities.stage.Stage;

import java.util.UUID;

public record StageResponse(UUID id, UUID roadmapId, String name) {

    public static StageResponse fromStage(Stage stage) {
        return new StageResponse(
                stage.getId(),
                stage.getRoadmapId(),
                stage.getName()
        );
    }

}
