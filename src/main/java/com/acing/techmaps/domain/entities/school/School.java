package com.acing.techmaps.domain.entities.school;

import java.util.UUID;

public class School {
    private UUID id;
    private String name;

    public School(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public School(String name) {
        this.name = name;
    }

    public static School createFull(UUID id, String name) {
        return new School(id, name);
    }

    public static School fromRequest(String name) {
        return new School(name);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
