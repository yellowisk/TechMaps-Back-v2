package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.school.SchoolRoadmapCRUD;
import com.acing.techmaps.web.model.school.request.SchoolRoadmapRequest;
import com.acing.techmaps.web.model.school.response.SchoolRoadmapResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v2/school-roadmaps")
public class SchoolRoadmapController {
    private final SchoolRoadmapCRUD schoolRoadmapCRUD;

    public SchoolRoadmapController(SchoolRoadmapCRUD schoolRoadmapCRUD) {
        this.schoolRoadmapCRUD = schoolRoadmapCRUD;
    }

    @PostMapping("")
    public ResponseEntity<SchoolRoadmapResponse> createSchoolRoadmap(
            @RequestBody SchoolRoadmapRequest request) {
        return ResponseEntity.ok(SchoolRoadmapResponse.fromSchoolRoadmap(schoolRoadmapCRUD.create(request)));
    }

    @GetMapping("/id/{schoolRoadmapId}")
    public ResponseEntity<SchoolRoadmapResponse> getSchoolRoadmapById(
            @PathVariable UUID schoolRoadmapId) {
        return ResponseEntity.ok(SchoolRoadmapResponse.fromSchoolRoadmap(schoolRoadmapCRUD.getById(schoolRoadmapId)));
    }

    @GetMapping("/schools/{schoolId}")
    public ResponseEntity<List<SchoolRoadmapResponse>> getSchoolRoadmapBySchoolId(
            @PathVariable UUID schoolId) {
        return ResponseEntity.ok(schoolRoadmapCRUD.getBySchoolId(schoolId)
                .stream().map(SchoolRoadmapResponse::fromSchoolRoadmap).toList());
    }

    @GetMapping("/roadmaps/{roadmapId}")
    public ResponseEntity<List<SchoolRoadmapResponse>> getSchoolRoadmapByRoadmapId(
            @PathVariable UUID roadmapId) {
        return ResponseEntity.ok(schoolRoadmapCRUD.getByRoadmapId(roadmapId)
                .stream().map(SchoolRoadmapResponse::fromSchoolRoadmap).toList());
    }

    @DeleteMapping("/{schoolRoadmapId}")
    public ResponseEntity<Void> deleteSchoolRoadmap(
            @PathVariable UUID schoolRoadmapId) {
        schoolRoadmapCRUD.delete(schoolRoadmapId);
        return ResponseEntity.noContent().build();
    }

}
