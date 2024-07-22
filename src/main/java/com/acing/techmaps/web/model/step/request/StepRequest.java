package com.acing.techmaps.web.model.step.request;

import com.acing.techmaps.domain.entities.step.Step;

import java.util.UUID;

public record StepRequest(UUID taskId, int position, String text, String link) {
    public StepRequest(UUID taskId, int position, String text, String link) {
        this.taskId = taskId;
        this.position = position;
        this.text = text;
        this.link = link;
    }

    public Step toStep() { return Step.fromRequest(taskId, position, text, link); }
}
