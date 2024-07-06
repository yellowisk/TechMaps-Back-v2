package com.acing.techmaps.domain.entities.stage;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Stage {
    private UUID  id;
    private UUID roadmapId;
    private String name;

    public Stage(UUID id, UUID roadmapId, String name) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.name = name;
    }

    public Stage(UUID roadmapId, String name) {
        this.roadmapId = roadmapId;
        this.name = name;
    }

    public static Stage fromRequest(UUID roadmapId, String name) {
        return new Stage(roadmapId, name);
    }

    public static Stage createFull(UUID id, UUID roadmapId, String name) {
        return new Stage(id, roadmapId, name);
    }
}
