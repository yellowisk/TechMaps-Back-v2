package com.acing.techmaps.usecases.roadmap;

import com.acing.techmaps.domain.entities.roadmap.RoadmapUser;
import com.acing.techmaps.web.model.roadmap.request.RoadmapUserRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface RoadmapUserCRUD {
    RoadmapUser create(RoadmapUserRequest request);
    RoadmapUser getById(UUID id);
    List<RoadmapUser> getByUserId(UUID userId);
    List<RoadmapUser> getByRoadmapId(UUID roadmapId);
    RoadmapUser updateEndTime(Timestamp endTime, UUID id);
    RoadmapUser updateIsDone(boolean isDone, UUID id);
    void delete(UUID id);
}