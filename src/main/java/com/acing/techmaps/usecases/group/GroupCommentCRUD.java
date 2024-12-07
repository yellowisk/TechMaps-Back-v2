package com.acing.techmaps.usecases.group;

import com.acing.techmaps.domain.entities.group.GroupComment;
import com.acing.techmaps.web.model.group.request.GroupCommentRequest;

import java.util.List;
import java.util.UUID;

public interface GroupCommentCRUD {
    GroupComment create(GroupCommentRequest request);
    GroupComment getById(UUID id);
    List<GroupComment> getByGroupPostId(UUID postId);
    List<GroupComment> getByUserId(UUID userId);
    GroupComment updateComment(GroupCommentRequest request, UUID id);
    void delete(UUID id);
}