package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.GroupPost;
import com.acing.techmaps.web.model.group.request.GroupPostRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface GroupPostCRUD {
    GroupPost create(GroupPostRequest request);
    GroupPost getById(UUID id);
    List<GroupPost> getByGroupId(UUID groupId);
    List<GroupPost> getByUserId(UUID userId);
    GroupPost updateGroupPost(GroupPostRequest request, UUID id);
    void delete(UUID id);
}
