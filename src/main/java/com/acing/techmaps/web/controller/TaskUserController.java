package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.task.TaskUserCRUD;
import com.acing.techmaps.web.model.task.request.TaskUserRequest;
import com.acing.techmaps.web.model.task.response.TaskUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v2/task-users")
public class TaskUserController {
    private final TaskUserCRUD taskUserCRUD;

    public TaskUserController(TaskUserCRUD taskUserCRUD) {
        this.taskUserCRUD = taskUserCRUD;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskUserResponse> getTaskUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(TaskUserResponse.createFromTaskUser(taskUserCRUD.getById(id)));
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskUserResponse> getTaskUserByTaskId(@PathVariable UUID taskId) {
        return ResponseEntity.ok(TaskUserResponse.createFromTaskUser(taskUserCRUD.getByTaskId(taskId)));
    }

    @GetMapping("/roadmap-users/{roadmapUserId}")
    public ResponseEntity<List<TaskUserResponse>> getTaskUserByRoadmapUserId(@PathVariable UUID roadmapUserId) {
        return ResponseEntity.ok(taskUserCRUD.getByRoadmapUserId(roadmapUserId)
                .stream().map(TaskUserResponse::createFromTaskUser).toList());
    }

    @PostMapping("")
    public ResponseEntity<TaskUserResponse> createTaskUser(@RequestBody TaskUserRequest request) {
        return ResponseEntity.ok(TaskUserResponse.createFromTaskUser(taskUserCRUD.create(request)));
    }

    @PatchMapping("/{id}/is-done/{isDone}")
    public ResponseEntity<TaskUserResponse> updateTaskUserIsDone(@PathVariable UUID id, @PathVariable boolean isDone) {
        return ResponseEntity.ok(TaskUserResponse.createFromTaskUser(taskUserCRUD.update(id, isDone)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskUser(@PathVariable UUID id) {
        taskUserCRUD.delete(id);
        return ResponseEntity.noContent().build();
    }

}
