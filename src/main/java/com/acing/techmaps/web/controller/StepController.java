package com.acing.techmaps.web.controller;

import com.acing.techmaps.domain.entities.step.Step;
import com.acing.techmaps.usecases.step.StepCRUD;
import com.acing.techmaps.web.model.step.request.StepRequest;
import com.acing.techmaps.web.model.step.response.StepResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/steps")
public class StepController {
    private final StepCRUD stepCRUD;

    public StepController(StepCRUD stepCRUD) {
        this.stepCRUD = stepCRUD;
    }

    @PostMapping("")
    public ResponseEntity<StepResponse> createStep(@RequestBody StepRequest request) {
        return ResponseEntity.ok(StepResponse.fromStep(stepCRUD.create(request)));
    }

    @GetMapping("/id/{stepId}")
    public ResponseEntity<StepResponse> getStepById(@PathVariable UUID stepId) {
        return ResponseEntity.ok(StepResponse.fromStep(stepCRUD.getById(stepId)));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<StepResponse>> getStepByTaskId(@PathVariable UUID taskId) {
        return ResponseEntity.ok(stepCRUD.getByTaskId(taskId)
                .stream().map(StepResponse::fromStep).toList());
    }

    @PatchMapping("/{stepId}")
    public ResponseEntity<StepResponse> updateStep(@PathVariable UUID stepId, @RequestBody StepRequest request) {
        Step step = stepCRUD.update(stepId, request);
        return ResponseEntity.ok(StepResponse.fromStep(step));
    }

    @DeleteMapping("/{stepId}")
    public ResponseEntity<Void> deleteStep(@PathVariable UUID stepId) {
        stepCRUD.delete(stepId);
        return ResponseEntity.noContent().build();
    }
}
