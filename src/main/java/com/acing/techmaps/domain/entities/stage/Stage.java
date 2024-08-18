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
    private int position;

    public Stage(UUID id, UUID roadmapId, String name, int position) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.name = name;
        this.position = position;
    }

    public Stage(UUID roadmapId, String name, int position) {
        this.roadmapId = roadmapId;
        this.name = name;
        this.position = position;
    }

    public static Stage fromRequest(UUID roadmapId, String name, int position) {
        return new Stage(roadmapId, name, position);
    }

    public static Stage createFull(UUID id, UUID roadmapId, String name, int position) {
        return new Stage(id, roadmapId, name, position);
    }
}
