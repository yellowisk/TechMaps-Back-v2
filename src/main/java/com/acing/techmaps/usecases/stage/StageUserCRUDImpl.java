package com.acing.techmaps.usecases.stage;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.usecases.stage.gateway.StageUserDAO;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StageUserCRUDImpl implements StageUserCRUD {
    private final StageUserDAO stageUserDAO;
    public StageUserCRUDImpl(StageUserDAO stageUserDAO) {
        this.stageUserDAO = stageUserDAO;
    }

    @Override
    public StageUser create(StageUserRequest request){
        return  stageUserDAO.add(request.toStageUser());
    }

    @Override
    public StageUser getById(UUID id){
        return stageUserDAO.findById(id);
    }

    @Override
    public List<StageUser> getByStageId(UUID stageId) {
        return stageUserDAO.findByStageId(stageId);
    }

    @Override
    public List<StageUser> getByUserId(UUID userId){
        return stageUserDAO.findByUserId(userId);
    }

    @Override
    public StageUser update(UUID id, boolean isCompleted) {
        StageUser stageUser = stageUserDAO.findById(id);
        stageUser.setCompleted(isCompleted);
        return stageUserDAO.update(stageUser);
    }

    @Override
    public void delete(UUID id){
        StageUser stageUser = stageUserDAO.findById(id);
        stageUserDAO.delete(stageUser);
    }
}
