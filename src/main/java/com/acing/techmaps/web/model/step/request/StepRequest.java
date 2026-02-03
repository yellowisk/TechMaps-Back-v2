package com.acing.techmaps.web.model.step.request;

import com.acing.techmaps.domain.entities.step.Step;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StepRequest(
        @NotNull UUID taskId,
        @Min(0) int position,
        @NotBlank String text,
        @NotBlank String link
) {

    public Step toStep() {
        return Step.fromRequest(taskId, position, text, link);
    }
}
