package com.acing.techmaps.web.model.task.request;

import com.acing.techmaps.domain.entities.task.Task;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TaskRequest(
        @NotNull UUID stageId,
        @NotBlank String title,
        @NotBlank String description,
        @Min(0) int position
) {

    public Task toTask() {
        return Task.fromRequest(stageId, title, description, position);
    }

}