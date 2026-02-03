package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.roadmap.RoadmapUserCRUD;
import com.acing.techmaps.web.model.roadmap.request.RoadmapUserRequest;
import com.acing.techmaps.web.model.roadmap.response.RoadmapUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/roadmap-users")
public class RoadmapUserController {

    private final RoadmapUserCRUD roadmapUserCRUD;

    @GetMapping("/{id}")
    public ResponseEntity<RoadmapUserResponse> getRoadmapUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(RoadmapUserResponse.createFromRoadmapUser(roadmapUserCRUD.getById(id)));
    }

    @GetMapping("/roadmaps/{roadmapId}")
    public ResponseEntity<List<RoadmapUserResponse>> getRoadmapUserByRoadmapId(@PathVariable UUID roadmapId) {
        return ResponseEntity.ok(roadmapUserCRUD.getByRoadmapId(roadmapId)
                .stream().map(RoadmapUserResponse::createFromRoadmapUser).toList());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<RoadmapUserResponse>> getRoadmapUserByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(roadmapUserCRUD.getByUserId(userId)
                .stream().map(RoadmapUserResponse::createFromRoadmapUser).toList());
    }

    @PostMapping("")
    public ResponseEntity<RoadmapUserResponse> createRoadmapUser(@RequestBody @Valid RoadmapUserRequest request) {
        return ResponseEntity.ok(RoadmapUserResponse.createFromRoadmapUser(roadmapUserCRUD.create(request)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoadmapUserResponse> updateEndTime(@PathVariable UUID id) {
        return ResponseEntity.ok(RoadmapUserResponse.createFromRoadmapUser(roadmapUserCRUD.updateEndTime(Timestamp.valueOf(LocalDateTime.now()), id)));
    }

    @PatchMapping("/{id}/is-done/{isDone}")
    public ResponseEntity<RoadmapUserResponse> updateRoadmapUserIsDone(@PathVariable UUID id, @PathVariable boolean isDone) {
        return ResponseEntity.ok(RoadmapUserResponse.createFromRoadmapUser(roadmapUserCRUD.updateIsDone(isDone, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoadmapUser(@PathVariable UUID id) {
        roadmapUserCRUD.delete(id);
        return ResponseEntity.noContent().build();
    }

}
