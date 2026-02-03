package com.acing.techmaps.web.model.stage.request;

import com.acing.techmaps.domain.entities.stage.Stage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StageRequest(
        @NotNull UUID roadmapId,
        @NotBlank String name,
        @Min(0) int position
) {

    public Stage toStage() {
        return Stage.fromRequest(roadmapId, name, position);
    }

}
