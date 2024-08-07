package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.school.SchoolCRUD;
import com.acing.techmaps.web.model.school.request.SchoolRequest;
import com.acing.techmaps.web.model.school.response.SchoolResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v2/schools")
public class SchoolController {
    private final SchoolCRUD schoolCRUD;

    public SchoolController(SchoolCRUD schoolCRUD) {
        this.schoolCRUD = schoolCRUD;
    }

    @PostMapping("")
    public ResponseEntity<SchoolResponse> createSchool(@RequestBody SchoolRequest request) {
        return ResponseEntity.ok(SchoolResponse.fromSchool(schoolCRUD.create(request)));
    }

    @GetMapping("/id/{schoolId}")
    public ResponseEntity<SchoolResponse> getSchoolById(@PathVariable UUID schoolId) {
        return ResponseEntity.ok(SchoolResponse.fromSchool(schoolCRUD.getById(schoolId)));
    }

    @GetMapping("/name/{schoolName}")
    public ResponseEntity<SchoolResponse> getSchoolByName(@PathVariable String schoolName) {
        return ResponseEntity.ok(SchoolResponse.fromSchool(schoolCRUD.getByName(schoolName)));
    }

    @PatchMapping("/{schoolId}")
    public ResponseEntity<SchoolResponse> updateSchool(@PathVariable UUID schoolId, @RequestBody SchoolRequest request) {
        return ResponseEntity.ok(SchoolResponse.fromSchool(schoolCRUD.updateSchool(request, schoolId)));
    }

}
