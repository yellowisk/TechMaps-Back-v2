package com.acing.techmaps.web.controller;

import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.usecases.stage.StageUserCRUD;
import com.acing.techmaps.web.model.stage.response.StageUserResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/stage-users")
public class StageUserController {
    private final StageUserCRUD stageUserCRUD;

    public StageUserController(StageUserCRUD stageUserCRUD) {
        this.stageUserCRUD = stageUserCRUD;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StageUserResponse> getStageUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.getById(id)));
    }
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<StageUserResponse>> getStageUserByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(stageUserCRUD.getByUserId(userId).stream().map(StageUserResponse::createFromStageUser).toList());
    }
    @GetMapping("/by-stage/{stageId}")
    public ResponseEntity<List<StageUserResponse>> getStageUserByStageId(@PathVariable UUID stageId) {
        return ResponseEntity.ok(stageUserCRUD.getByStageId(stageId).stream().map(StageUserResponse::createFromStageUser).toList());
    }
    @PatchMapping("/{id}/is-Completed/{isCompleted}")
    public ResponseEntity<StageUserResponse> updateTaskUserIsDone(@PathVariable UUID id, @PathVariable boolean isCompleted) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.update(id,isCompleted)));
    }
}
