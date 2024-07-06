package com.acing.techmaps.usecases.stage.gateway;

import com.acing.techmaps.domain.entities.stage.Stage;

import java.util.List;
import java.util.UUID;

public interface StageDAO {
    Stage add(Stage stage);
    Stage findById(UUID id);
    List<Stage> findByRoadmap(UUID roadmapId);
    Stage update(Stage stage);
    void delete(Stage stage);
    Boolean stageExists(UUID id);
}
