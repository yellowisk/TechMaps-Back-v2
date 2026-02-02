package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.stage.StageCRUD;
import com.acing.techmaps.web.model.stage.request.StageRequest;
import com.acing.techmaps.web.model.stage.response.StageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/stages")
public class StageController {

    private final StageCRUD stageCRUD;

    public StageController(StageCRUD stageCRUD) {
        this.stageCRUD = stageCRUD;
    }

    @PostMapping("")
    public ResponseEntity<StageResponse> createStage(@RequestBody StageRequest request) {
        return ResponseEntity.ok(StageResponse.fromStage(stageCRUD.create(request)));
    }

    @GetMapping("/id/{stageId}")
    public ResponseEntity<StageResponse> getStageById(@PathVariable UUID stageId) {
        return ResponseEntity.ok(StageResponse.fromStage(stageCRUD.getById(stageId)));
    }

    @GetMapping("/roadmaps/{roadmapId}")
    public ResponseEntity<List<StageResponse>> getStageByRoadmapId(@PathVariable UUID roadmapId) {
        return ResponseEntity.ok(stageCRUD.getByRoadmapId(roadmapId)
                .stream().map(StageResponse::fromStage).toList());
    }

    @PatchMapping("/{stageId}/name/{name}/position/{position}")
    public ResponseEntity<StageResponse> updateStageName(
            @PathVariable UUID stageId, @PathVariable String name,
            @PathVariable int position) {
        return ResponseEntity.ok(StageResponse.fromStage(stageCRUD.update(stageId, name, position)));
    }

    @DeleteMapping("/{stageId}")
    public ResponseEntity<Void> deleteStage(@PathVariable UUID stageId) {
        stageCRUD.delete(stageId);
        return ResponseEntity.noContent().build();
    }

}
