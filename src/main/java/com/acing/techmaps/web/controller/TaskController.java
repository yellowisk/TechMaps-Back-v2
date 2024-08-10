package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.task.TaskCRUD;
import com.acing.techmaps.web.model.task.request.TaskRequest;
import com.acing.techmaps.web.model.task.request.TaskUpdateRequest;
import com.acing.techmaps.web.model.task.response.TaskResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v2/tasks")
public class TaskController {
    private final TaskCRUD taskCRUD;

    @PostMapping("")
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest request) {
        return ResponseEntity.ok(TaskResponse.createFromTask(taskCRUD.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(TaskResponse.createFromTask(taskCRUD.getById(id)));
    }

    @GetMapping("/stages/{stageId}")
    public ResponseEntity<List<TaskResponse>> getByStageId(@PathVariable UUID stageId) {
        return ResponseEntity.ok(taskCRUD.getByStageId(stageId)
                .stream().map(TaskResponse::createFromTask).toList());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updateStatus(@PathVariable UUID id, @RequestBody TaskUpdateRequest request) {
        return ResponseEntity.ok(TaskResponse.createFromTask(taskCRUD.update(request, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        taskCRUD.delete(id);
        return ResponseEntity.noContent().build();
    }
}
