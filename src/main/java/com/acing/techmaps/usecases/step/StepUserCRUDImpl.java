package com.acing.techmaps.usecases.step;

import com.acing.techmaps.domain.entities.step.StepUser;
import com.acing.techmaps.usecases.step.gateway.StepUserDAO;
import com.acing.techmaps.web.model.step.request.StepUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StepUserCRUDImpl implements StepUserCRUD {
    private final StepUserDAO stepUserDAO;

    @Override
    public StepUser create(StepUserRequest stepUserRequest) {
        return stepUserDAO.add(stepUserRequest.toStepUser());
    }

    @Override
    public StepUser getById(UUID id) {
        return stepUserDAO.findById(id);
    }

    @Override
    public List<StepUser> getByRoadmapUserId(UUID roadmapUserId) {
        return stepUserDAO.findByRoadmapUserId(roadmapUserId);
    }

    @Override
    public List<StepUser> getByStepId(UUID stepId) {
        return stepUserDAO.findByStepId(stepId);
    }

    @Override
    public StepUser updateStatus(boolean isDone, UUID id) {
        StepUser stepUser = stepUserDAO.findById(id);
        stepUser.setDone(isDone);
        return stepUserDAO.updateStatus(stepUser);
    }

    @Override
    public void delete(UUID id) {
        stepUserDAO.delete(id);
    }
}
