package com.acing.techmaps.domain.entities.step;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Step {
    private UUID id;
    private UUID taskId;
    private int position;
    private String text;
    private String link;

    public Step(UUID id, UUID taskId, int position, String text, String link) {
        this.id = id;
        this.taskId = taskId;
        this.position = position;
        this.text = text;
        this.link = link;
    }

    public Step(UUID taskId, int position, String text, String link) {
        this.taskId = taskId;
        this.position = position;
        this.text = text;
        this.link = link;
    }

    public static Step fromRequest(UUID taskId, int position, String text, String link) {
        return new Step(taskId, position, text, link);
    }

    public static Step createFull(UUID id, UUID taskId, int position, String text, String link) {
        return new Step(id, taskId, position, text, link);
    }
}
