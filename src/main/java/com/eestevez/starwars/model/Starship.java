package com.eestevez.starwars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

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
}
