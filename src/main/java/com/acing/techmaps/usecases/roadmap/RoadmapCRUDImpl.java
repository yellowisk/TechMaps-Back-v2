package com.acing.techmaps.usecases.roadmap;

import com.acing.techmaps.domain.entities.roadmap.Roadmap;
import com.acing.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import com.acing.techmaps.web.model.roadmap.request.RoadmapRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoadmapCRUDImpl implements RoadmapCRUD{
    private final RoadmapDAO roadmapDAO;

    public RoadmapCRUDImpl(RoadmapDAO roadmapDAO) {
        this.roadmapDAO = roadmapDAO;
    }

    @Override
    public Roadmap create(RoadmapRequest request) {
        return roadmapDAO.add(request.toRoadmap());
    }

    @Override
    public Roadmap getById(UUID id) {
        return roadmapDAO.findById(id).get();
    }

    @Override
    public Roadmap getByName(String name) {
        return roadmapDAO.findByName(name).get();
    }

    @Override
    public Roadmap updateRoadmap(RoadmapRequest request, UUID id) {
        Roadmap roadmap = request.toRoadmap();
        roadmap.setId(id);
        return roadmapDAO.update(roadmap);
    }
}
