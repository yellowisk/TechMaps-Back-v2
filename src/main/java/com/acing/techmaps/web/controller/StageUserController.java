package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.stage.StageUserCRUD;
import com.acing.techmaps.web.model.stage.request.StageUserRequest;
import com.acing.techmaps.web.model.stage.response.StageUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/stage-users")
public class StageUserController {
    private final StageUserCRUD stageUserCRUD;

    @PostMapping("")
    public ResponseEntity<StageUserResponse> create(@RequestBody @Valid StageUserRequest request) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StageUserResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.getById(id)));
    }

    @GetMapping("/stages/{stageId}")
    public ResponseEntity<StageUserResponse> getByStageId(@PathVariable UUID stageId) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.getByStageId(stageId)));
    }

    @GetMapping("/roadmap-users/{roadmapUserId}")
    public ResponseEntity<List<StageUserResponse>> getByRoadmapUserId(@PathVariable UUID roadmapUserId) {
        return ResponseEntity.ok(stageUserCRUD.getByRoadmapUserId(roadmapUserId)
                .stream().map(StageUserResponse::createFromStageUser).toList());
    }

    @PatchMapping("/{id}/is-done/{isDone}/position/{position}")
    public ResponseEntity<StageUserResponse> updateStatus(@PathVariable UUID id, @PathVariable boolean isDone, @PathVariable int position) {
        return ResponseEntity.ok(StageUserResponse.createFromStageUser(stageUserCRUD.update(isDone, position, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        stageUserCRUD.delete(id);
        return ResponseEntity.noContent().build();
    }
}
