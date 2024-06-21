package com.acing.techmaps.domain.entities.roadmap;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Roadmap {
    private UUID id;
    private String name;
    private Language language;

    public Roadmap(UUID id, String name, Language language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public Roadmap(String name, Language language) {
        this.name = name;
        this.language = language;
    }

    public static Roadmap createFull(UUID id, String name, Language language) {
        return new Roadmap(id, name, language);
    }

    public static Roadmap fromRequest(String name, String language) {
        return new Roadmap(name, Language.valueOf(language));
    }

}
