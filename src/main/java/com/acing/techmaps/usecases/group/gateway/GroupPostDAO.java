package com.acing.techmaps.usecases.group.gateway;

import com.acing.techmaps.domain.entities.group.GroupPost;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface GroupPostDAO {
    GroupPost add(GroupPost groupPost);
    GroupPost findById(UUID id);
    List<GroupPost> findByGroupId(UUID groupId);
    List<GroupPost> findByUserId(UUID userId);
    GroupPost update(GroupPost groupPost, UUID id);
    void delete(UUID groupPostId);
}
