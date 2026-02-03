package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.step.StepUserCRUD;
import com.acing.techmaps.web.model.step.request.StepUserRequest;
import com.acing.techmaps.web.model.step.response.StepUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/step-users")
public class StepUserController {
    private final StepUserCRUD stepUserCRUD;

    @PostMapping("")
    public ResponseEntity<StepUserResponse> create(@RequestBody @Valid StepUserRequest request) {
        return ResponseEntity.ok(StepUserResponse.createFromStepUser(stepUserCRUD.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepUserResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(StepUserResponse.createFromStepUser(stepUserCRUD.getById(id)));
    }

    @GetMapping("/steps/{stepId}")
    public ResponseEntity<List<StepUserResponse>> getByStepId(@PathVariable UUID stepId) {
        return ResponseEntity.ok(stepUserCRUD.getByStepId(stepId).stream().map(StepUserResponse::createFromStepUser).toList());
    }

    @GetMapping("/roadmap-user/{roadmapUserId}")
    public ResponseEntity<List<StepUserResponse>> getByRoadmapUserId(@PathVariable UUID roadmapUserId) {
        return ResponseEntity.ok(stepUserCRUD.getByRoadmapUserId(roadmapUserId).stream().map(StepUserResponse::createFromStepUser).toList());
    }

    @PatchMapping("/{id}/is-done/{isDone}")
    public ResponseEntity<StepUserResponse> updateStatus(@PathVariable UUID id, @PathVariable boolean isDone) {
        return ResponseEntity.ok(StepUserResponse.createFromStepUser(stepUserCRUD.updateStatus(isDone, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        stepUserCRUD.delete(id);
        return ResponseEntity.noContent().build();
    }
}