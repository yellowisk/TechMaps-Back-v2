package com.acing.techmaps.usecases.stage;

import com.acing.techmaps.domain.entities.stage.Stage;
import com.acing.techmaps.usecases.stage.gateway.StageDAO;
import com.acing.techmaps.web.model.stage.request.StageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StageCRUDImpl implements StageCRUD {

    private final StageDAO stageDAO;

    public StageCRUDImpl(StageDAO stageDAO) {
        this.stageDAO = stageDAO;
    }

    @Override
    public Stage create(StageRequest request) {
        return stageDAO.add(request.toStage());
    }

    @Override
    public Stage getById(UUID id) {
        return stageDAO.findById(id);
    }

    @Override
    public List<Stage> getByRoadmapId(UUID roadmapId) {
        return stageDAO.findByRoadmap(roadmapId);
    }

    @Override
    public Stage update(UUID id, String name, int position) {
        Stage stage = stageDAO.findById(id);
        stage.setName(name);
        stage.setPosition(position);
        return stageDAO.update(stage);
    }

    @Override
    public void delete(UUID id) {
        Stage stage = stageDAO.findById(id);
        stageDAO.delete(stage);
    }
}
