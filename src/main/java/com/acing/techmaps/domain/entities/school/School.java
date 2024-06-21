package com.acing.techmaps.domain.entities.school;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
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

}
