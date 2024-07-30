package com.acing.techmaps.domain.entities.step;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StepUser {
    private UUID id;
    private UUID stepId;
    private UUID roadmapUserId;
    private boolean isDone;

    /**
     * Create new StepUser from database (with assigned id).
     * @param id Database ID of `StepUser` entity.
     * @param stepId Database ID of linked {@link Step step}.
     * @param roadmapUserId Database ID of linked {@link com.acing.techmaps.domain.entities.roadmap.RoadmapUser roadmap-user}.
     * @param isDone Whether the step is done by the user.
     */
    public StepUser(UUID id, UUID stepId, UUID roadmapUserId, boolean isDone) {
        this.id = id;
        this.stepId = stepId;
        this.roadmapUserId = roadmapUserId;
        this.isDone = isDone;
    }

    /**
     * Create new StepUser from user request.
     * @param stepId Database ID of linked {@link Step step}.
     * @param roadmapUserId Database ID of linked {@link com.acing.techmaps.domain.entities.roadmap.RoadmapUser roadmap-user}.
     * @param isDone Whether the step is done by the user.
     */
    public StepUser(UUID stepId, UUID roadmapUserId, boolean isDone) {
        this.stepId = stepId;
        this.roadmapUserId = roadmapUserId;
        this.isDone = isDone;
    }
}
