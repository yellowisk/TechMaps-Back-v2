package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.stage_user.Stage_UserCRUD;
import com.acing.techmaps.web.model.stage.response.StageUserResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class StageUserController {
    private final Stage_UserCRUD stageUserCRUD;

    public StageUserController(Stage_UserCRUD stageUserCRUD) {
        this.stageUserCRUD = stageUserCRUD;
    }
    @GetMapping("api/v1/stage-users/{id}")
    public ResponseEntity<StageUserResponse> getStageUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.getById(id)));
    }
    @GetMapping("api/v1/stage-users/tasks/{taskId}")
    public ResponseEntity<StageUserResponse> getStageUserByStageId(@PathVariable UUID stageId) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.getByStageId(stageId)));
    }
    @PatchMapping("api/v1/stage-users/{id}/is-Completed/{isCompleted}")
    public ResponseEntity<StageUserResponse> updateTaskUserIsDone(@PathVariable UUID id, @PathVariable boolean isCompleted) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.update(id,isCompleted)));
    }


}
