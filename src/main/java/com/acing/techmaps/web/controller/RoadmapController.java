package com.acing.techmaps.web.controller;

import com.acing.techmaps.domain.entities.roadmap.Roadmap;
import com.acing.techmaps.usecases.roadmap.RoadmapCRUD;
import com.acing.techmaps.web.model.roadmap.request.RoadmapRequest;
import com.acing.techmaps.web.model.roadmap.response.RoadmapResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class RoadmapController {
    private final RoadmapCRUD roadmapCRUD;

    public RoadmapController(RoadmapCRUD roadmapCRUD) {
        this.roadmapCRUD = roadmapCRUD;
    }

    @PostMapping("/api/v1/roadmaps")
    public ResponseEntity<RoadmapResponse> createRoadmap(@RequestBody RoadmapRequest request) {
        Roadmap roadmap = roadmapCRUD.create(request);
        return ResponseEntity.ok(RoadmapResponse.fromRoadmap(roadmap));
    }

    @GetMapping("/api/v1/roadmaps/id/{roadmapId}")
    public ResponseEntity<RoadmapResponse> getRoadmapById(@PathVariable UUID roadmapId) {
        Roadmap roadmap = roadmapCRUD.getById(roadmapId);
        return ResponseEntity.ok(RoadmapResponse.fromRoadmap(roadmap));
    }

    @GetMapping("/api/v1/roadmaps/name/{roadmapName}")
    public ResponseEntity<RoadmapResponse> getRoadmapByName(@PathVariable String roadmapName) {
        Roadmap roadmap = roadmapCRUD.getByName(roadmapName);
        return ResponseEntity.ok(RoadmapResponse.fromRoadmap(roadmap));
    }

    @PatchMapping("/api/v1/roadmaps/{roadmapId}")
    public ResponseEntity<RoadmapResponse> updateRoadmap(@PathVariable UUID roadmapId, @RequestBody RoadmapRequest request) {
        Roadmap roadmap = roadmapCRUD.updateRoadmap(request, roadmapId);
        return ResponseEntity.ok(RoadmapResponse.fromRoadmap(roadmap));
    }

}
