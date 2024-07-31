package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.stage_user.Stage_UserCRUD;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;
import com.acing.techmaps.web.model.stage.response.StageUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/stage-users")
@RestController
public class StageUserController {
    private final Stage_UserCRUD stageUserCRUD;

   public StageUserController(Stage_UserCRUD stageUserCRUD) {
        this.stageUserCRUD = stageUserCRUD;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StageUserResponse> getStageUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.getById(id)));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<StageUserResponse> getStageUserByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.getByUserId(userId)));
    }

    @GetMapping("/stages/{stageId}")
    public ResponseEntity<List<StageUserResponse>> getStageUserByStageId(@PathVariable UUID stageId) {
        return ResponseEntity.ok(stageUserCRUD.getByStageId(stageId)
                .stream().map(StageUserResponse::createFromStageUser).toList());
    }

    @PostMapping("")
    public ResponseEntity<StageUserResponse> createStageUser(@RequestBody StageUserRequest request) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.create(request)));
    }

    @PatchMapping("/{id}/is-Completed/{isCompleted}")
    public ResponseEntity<StageUserResponse> updateStageUserIsDone(@PathVariable UUID id, @PathVariable boolean isCompleted) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.update(id, isCompleted)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStageUser(@PathVariable UUID id) {
        stageUserCRUD.delete(id);
        return ResponseEntity.noContent().build();
    }
}
