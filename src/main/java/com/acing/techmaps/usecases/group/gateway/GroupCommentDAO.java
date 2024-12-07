package com.acing.techmaps.usecases.group.gateway;

import com.acing.techmaps.domain.entities.group.GroupComment;

import java.util.List;
import java.util.UUID;

public interface GroupCommentDAO {
    GroupComment add(GroupComment groupComment);
    GroupComment findById(UUID id);
    List<GroupComment> findByGroupPostId(UUID groupPostId);
    List<GroupComment> findByUserId(UUID userId);
    GroupComment update(GroupComment groupComment, UUID id);
    void delete(UUID groupPostId);
}
