package com.acing.techmaps.web.model.step.response;

import com.acing.techmaps.domain.entities.step.Step;

import java.util.UUID;

public record StepResponse(UUID id, UUID taskId, int position, String text, String link) {

    public static StepResponse fromStep(Step step) {
        return new StepResponse(
                step.getId(),
                step.getTaskId(),
                step.getPosition(),
                step.getText(),
                step.getLink()
        );
    }
}
