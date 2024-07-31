package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.task.TaskCRUD;
import com.acing.techmaps.web.model.task.request.TaskRequest;
import com.acing.techmaps.web.model.task.response.TaskResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TaskController {
    private final TaskCRUD taskCRUD;

    public TaskController(TaskCRUD taskCRUD) {
        this.taskCRUD = taskCRUD;
    }

    @PostMapping("api/v1/tasks")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return ResponseEntity.ok(TaskResponse.fromTask(taskCRUD.create(request)));
    }

    @GetMapping("api/v1/tasks/id/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID taskId) {
        return ResponseEntity.ok(TaskResponse.fromTask(taskCRUD.getById(taskId)));
    }

    @GetMapping("api/v1/tasks/task/{taskId}")
    public ResponseEntity<List<TaskResponse>> getTaskByTaskId(@PathVariable UUID taskId) {
        return ResponseEntity.ok(taskCRUD.getByTask(taskId)
                .stream().map(TaskResponse::fromTask).toList());
    }

    @PatchMapping("api/v1/tasks/{taskId}/name/{name}")
    public ResponseEntity<TaskResponse> updateTaskName(
            @PathVariable UUID taskId, @PathVariable String name) {
        return ResponseEntity.ok(TaskResponse.fromTask(taskCRUD.update(taskId, name)));
    }

    @DeleteMapping("api/v1/stages/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskCRUD.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
