package com.acing.techmaps.domain.entities.roadmap;

import java.util.UUID;

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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
