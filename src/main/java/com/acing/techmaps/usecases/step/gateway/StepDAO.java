package com.acing.techmaps.usecases.step.gateway;

import com.acing.techmaps.domain.entities.step.Step;

import java.util.List;
import java.util.UUID;

public interface StepDAO {
    Step add(Step step);
    Step findById(UUID id);
    List<Step> findByTask(UUID taskId);
    Step update(Step step);
    void delete(Step step);
    // stepExists not implemented
}
