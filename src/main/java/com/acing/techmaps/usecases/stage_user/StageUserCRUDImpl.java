package com.acing.techmaps.usecases.stage_user;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.usecases.stage_user.gateway.Stage_UserDAO;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StageUserCRUDImpl implements Stage_UserCRUD {
    private final Stage_UserDAO stageUserDAO;

    public StageUserCRUDImpl(Stage_UserDAO stageUserDAO) {
        this.stageUserDAO = stageUserDAO;
    }

    @Override
    public StageUser create(StageUserRequest request) {
        return stageUserDAO.add(request.toStageUser());
    }

    @Override
    public StageUser getById(UUID id) {
        return stageUserDAO.findById(id);
    }

    @Override
    public StageUser getByUserId(UUID userId) {
        return stageUserDAO.findByUserId(userId);
    }

    @Override
    public List<StageUser> getByStageId(UUID stageId) {
        return stageUserDAO.findByStageId(stageId);
    }

    @Override
    public StageUser update(UUID id, Boolean isCompleted) {
        StageUser stageUser = stageUserDAO.findById(id);
        stageUser.setIsCompleted(isCompleted);
        return stageUserDAO.update(stageUser);
    }

    @Override
    public void delete(UUID id) {
        StageUser stageUser = stageUserDAO.findById(id);
        stageUserDAO.delete(stageUser);
    }
}

