package com.acing.techmaps.web.model.step.request;

import com.acing.techmaps.domain.entities.step.Step;

import java.util.UUID;

public record StepRequest(UUID taskId, int position, String text, String link) {

    public Step toStep() { return Step.fromRequest(taskId, position, text, link); }
}
