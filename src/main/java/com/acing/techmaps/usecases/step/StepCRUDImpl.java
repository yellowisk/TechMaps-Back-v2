package com.acing.techmaps.usecases.step;

import com.acing.techmaps.domain.entities.step.Step;
import com.acing.techmaps.usecases.step.gateway.StepDAO;
import com.acing.techmaps.web.model.step.request.StepRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StepCRUDImpl implements StepCRUD {
    private final StepDAO stepDAO;

    public StepCRUDImpl(StepDAO stepDAO) {
        this.stepDAO = stepDAO;
    }

    @Override
    public Step create(StepRequest request) {
        return stepDAO.add(request.toStep());
    }

    @Override
    public Step getById(UUID id) {
        return stepDAO.findById(id);
    }

    @Override
    public List<Step> getByTaskId(UUID taskId) {
        return stepDAO.findByTask(taskId);
    }

    @Override
    public Step update(UUID id, StepRequest request) {
        Step step = request.toStep();
        step.setId(id);
        return stepDAO.update(step);
    }

    @Override
    public void delete(UUID id) {
        Step step = stepDAO.findById(id);
        stepDAO.delete(step);
    }
}
