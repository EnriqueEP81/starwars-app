package com.eestevez.starwars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public record Starship (
        String name,
        String model,
        String length,
        @JsonProperty("max_atmosphering_speed") String speed,
        String crew,
        String passengers,
        @JsonProperty("starship_class") String type,
        OffsetDateTime created,
        OffsetDateTime edited) {
    public String getCreatedFormatted() {
        return created != null ? created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "";
    }

    public String getEditedFormatted() {
        return edited != null ? edited.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "";
    }

}
