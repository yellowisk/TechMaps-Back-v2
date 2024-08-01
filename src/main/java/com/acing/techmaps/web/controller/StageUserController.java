package com.acing.techmaps.web.controller;

<<<<<<< HEAD
import com.acing.techmaps.usecases.stage_user.Stage_UserCRUD;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;
import com.acing.techmaps.web.model.stage.response.StageUserResponse;
=======
import com.acing.techmaps.domain.entities.stage.StageUser;
import com.acing.techmaps.usecases.stage.StageUserCRUD;
import com.acing.techmaps.web.model.stage.response.StageUserResponse;

>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

<<<<<<< HEAD
@RequestMapping("api/v1/stage-users")
@RestController
public class StageUserController {
    private final Stage_UserCRUD stageUserCRUD;

   public StageUserController(Stage_UserCRUD stageUserCRUD) {
=======
@RestController
@RequestMapping("/api/v2/stage-users")
public class StageUserController {
    private final StageUserCRUD stageUserCRUD;

    public StageUserController(StageUserCRUD stageUserCRUD) {
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
        this.stageUserCRUD = stageUserCRUD;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StageUserResponse> getStageUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.getById(id)));
    }
<<<<<<< HEAD

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
=======
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
>>>>>>> 57a37660c66285f13e2eba2a805f51a04f2250dc
    }
}
