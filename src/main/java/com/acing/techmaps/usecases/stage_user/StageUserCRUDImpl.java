package com.acing.techmaps.usecases.stage_user;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.usecases.stage_user.gateway.Stage_UserDAO;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StageUserCRUDImpl {
    private final Stage_UserDAO stageUserDAO;

    public StageUserCRUDImpl(Stage_UserDAO stageUserDAO) {
        this.stageUserDAO = stageUserDAO;
    }


    public StageUser create(StageUserRequest request){
        return  stageUserDAO.add(request.toStageUser());
    }


    public StageUser getById(UUID id){
        return stageUserDAO.findById(id);
    }


    public StageUser getByStageId(UUID stageId) {
        return stageUserDAO.findByStageId(stageId);
    }

    public List<StageUser>getByUserId(UUID userId){
        return stageUserDAO.findByUserId(userId);
    }

    public StageUser update(UUID id, Boolean isCompleted){
        StageUser stageUser = stageUserDAO.findById(id);
        stageUser.setCompleted(isCompleted);
        return stageUserDAO.update(stageUser);
    }

    public void delete(UUID id){
        StageUser stageUser = stageUserDAO.findById(id);
        stageUserDAO.delete(stageUser);
    }
}
