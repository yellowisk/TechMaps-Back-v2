package com.acing.techmaps.usecases.step;

import com.acing.techmaps.domain.entities.step.Step;
import com.acing.techmaps.web.model.step.request.StepRequest;

import java.util.List;
import java.util.UUID;

public interface StepCRUD {
    Step create(StepRequest request);
    Step getById(UUID id);
    List<Step> getByTaskId(UUID taskId);
    Step update(UUID id, StepRequest request);
    void delete(UUID id);
}
