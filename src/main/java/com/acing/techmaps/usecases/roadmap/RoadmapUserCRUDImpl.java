package com.acing.techmaps.usecases.roadmap;

import com.acing.techmaps.domain.entities.roadmap.RoadmapUser;
import com.acing.techmaps.usecases.roadmap.gateway.RoadmapUserDAO;
import com.acing.techmaps.web.model.roadmap.request.RoadmapUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoadmapUserCRUDImpl implements RoadmapUserCRUD {
    private final RoadmapUserDAO roadmapUserDAO;

    @Override
    public RoadmapUser create(RoadmapUserRequest request) {
        return roadmapUserDAO.add(request.toRoadmapUser());
    }

    @Override
    public RoadmapUser getById(UUID id) {
        return roadmapUserDAO.findById(id);
    }

    @Override
    public List<RoadmapUser> getByUserId(UUID userId) {
        return roadmapUserDAO.findByUserId(userId);
    }

    @Override
    public List<RoadmapUser> getByRoadmapId(UUID roadmapId) {
        return roadmapUserDAO.findByRoadmapId(roadmapId);
    }

    @Override
    public RoadmapUser updateEndTime(Timestamp endTime, UUID id) {
        RoadmapUser roadmapUser = roadmapUserDAO.findById(id);
        roadmapUser.setEndTime(endTime);
        return roadmapUserDAO.updateEndTime(roadmapUser);
    }

    @Override
    public RoadmapUser updateIsDone(boolean isDone, UUID id) {
        RoadmapUser roadmapUser = roadmapUserDAO.findById(id);
        roadmapUser.setIsDone(isDone);
        return roadmapUserDAO.updateIsDone(roadmapUser);
    }

    @Override
    public void delete(UUID id) {
        roadmapUserDAO.delete(id);
    }
}
