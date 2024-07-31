package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.school.SchoolUserCRUD;
import com.acing.techmaps.web.model.school.request.SchoolUserRequest;
import com.acing.techmaps.web.model.school.response.SchoolUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v2/school-users")
public class SchoolUserController {

    private final SchoolUserCRUD schoolUserCRUD;

    public SchoolUserController(SchoolUserCRUD schoolUserCRUD) {
        this.schoolUserCRUD = schoolUserCRUD;
    }

    @GetMapping("/id/{schoolUserId}")
    public ResponseEntity<SchoolUserResponse> getSchoolUserById(@PathVariable  UUID schoolUserId) {
        return ResponseEntity.ok(SchoolUserResponse.fromSchoolUser(schoolUserCRUD.getById(schoolUserId)));
    }

    @GetMapping("/schools/{schoolId}")
    public ResponseEntity<List<SchoolUserResponse>> getSchoolUserBySchoolId(@PathVariable UUID schoolId) {
        return ResponseEntity.ok(schoolUserCRUD.getBySchoolId(schoolId)
                .stream().map(SchoolUserResponse::fromSchoolUser).toList());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<SchoolUserResponse>> getSchoolUserByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(schoolUserCRUD.getByUserId(userId)
                .stream().map(SchoolUserResponse::fromSchoolUser).toList());
    }

    @PostMapping("")
    public ResponseEntity<SchoolUserResponse> createSchoolUser(
            @RequestBody SchoolUserRequest request) {
        return ResponseEntity.ok(SchoolUserResponse.fromSchoolUser(schoolUserCRUD.create(request)));
    }

    @PatchMapping("/{schoolUserId}/role/{role}")
    public ResponseEntity<SchoolUserResponse> updateSchoolUserRole(
            @PathVariable UUID schoolUserId, @PathVariable String role) {
        return ResponseEntity.ok(SchoolUserResponse.fromSchoolUser(schoolUserCRUD.updateRole(schoolUserId, role)));
    }

    @DeleteMapping("/{schoolUserId}")
    public ResponseEntity<Void> deleteSchoolUser(@PathVariable UUID schoolUserId) {
        schoolUserCRUD.delete(schoolUserId);
        return ResponseEntity.noContent().build();
    }

}
