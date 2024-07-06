package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.stage.StageCRUD;
import com.acing.techmaps.web.model.stage.request.StageRequest;
import com.acing.techmaps.web.model.stage.response.StageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StageController {

    private final StageCRUD stageCRUD;

    public StageController(StageCRUD stageCRUD) {
        this.stageCRUD = stageCRUD;
    }

    @PostMapping("api/v1/stages")
    public ResponseEntity<StageResponse> createStage(@RequestBody StageRequest request) {
        return ResponseEntity.ok(StageResponse.fromStage(stageCRUD.create(request)));
    }

    @GetMapping("api/v1/stages/id/{stageId}")
    public ResponseEntity<StageResponse> getStageById(@PathVariable UUID stageId) {
        return ResponseEntity.ok(StageResponse.fromStage(stageCRUD.getById(stageId)));
    }

    @GetMapping("api/v1/stages/roadmap/{roadmapId}")
    public ResponseEntity<List<StageResponse>> getStageByRoadmapId(@PathVariable UUID roadmapId) {
        return ResponseEntity.ok(stageCRUD.getByRoadmapId(roadmapId)
                .stream().map(StageResponse::fromStage).toList());
    }

    @PatchMapping("api/v1/stages/{stageId}/name/{name}")
    public ResponseEntity<StageResponse> updateStageName(
            @PathVariable UUID stageId, @PathVariable String name) {
        return ResponseEntity.ok(StageResponse.fromStage(stageCRUD.update(stageId, name)));
    }

    @DeleteMapping("api/v1/stages/{stageId}")
    public ResponseEntity<Void> deleteStage(@PathVariable UUID stageId) {
        stageCRUD.delete(stageId);
        return ResponseEntity.noContent().build();
    }

}
