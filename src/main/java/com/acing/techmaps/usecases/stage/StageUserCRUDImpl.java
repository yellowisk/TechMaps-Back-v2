package com.acing.techmaps.usecases.stage;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.usecases.stage.gateway.StageUserDAO;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StageUserCRUDImpl implements StageUserCRUD {
    private final StageUserDAO stageUserDAO;

    @Override
    public StageUser create(StageUserRequest request) {
        return stageUserDAO.add(request.toStageUser());
    }

    @Override
    public StageUser getById(UUID id) {
        return stageUserDAO.findById(id);
    }

    @Override
    public StageUser getByStageId(UUID stageId) {
        return stageUserDAO.findByStageId(stageId);
    }

    @Override
    public List<StageUser> getByRoadmapUserId(UUID roadmapUserId) {
        return stageUserDAO.findByRoadmapUserId(roadmapUserId);
    }

    @Override
    public StageUser update(boolean isDone, int position, UUID id) {
        StageUser stageUser = stageUserDAO.findById(id);
        stageUser.setDone(isDone);
        stageUser.setPosition(position);
        return stageUserDAO.update(stageUser);
    }

    @Override
    public void delete(UUID id) {
        StageUser stageUser = stageUserDAO.findById(id);
        stageUserDAO.delete(stageUser);
    }
}
