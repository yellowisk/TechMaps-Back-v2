package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.group.GroupCRUD;
import com.acing.techmaps.web.model.group.request.GroupRequest;
import com.acing.techmaps.web.model.group.response.GroupResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v2/groups")
public class GroupController {
    private final GroupCRUD groupCRUD;

    public GroupController(GroupCRUD groupCRUD) {
        this.groupCRUD = groupCRUD;
    }

    @PostMapping("")
    public ResponseEntity<GroupResponse> createGroup(@RequestBody GroupRequest request) {
        return ResponseEntity.ok(GroupResponse.fromGroup(groupCRUD.create(request)));
    }

    @GetMapping("/id/{groupId}")
    public ResponseEntity<GroupResponse> getGroupById(@PathVariable UUID groupId) {
        return ResponseEntity.ok(GroupResponse.fromGroup(groupCRUD.getById(groupId)));
    }

    @GetMapping("/name/{groupName}")
    public ResponseEntity<GroupResponse> getGroupByName(@PathVariable String groupName) {
        return ResponseEntity.ok(GroupResponse.fromGroup(groupCRUD.getByName(groupName)));
    }

    @PatchMapping("/{groupId}")
    public ResponseEntity<GroupResponse> updateGroup(@PathVariable UUID groupId,
                                                      @RequestBody GroupRequest request) {
        return ResponseEntity.ok(GroupResponse.fromGroup(groupCRUD.updateGroup(request, groupId)));
    }

}
