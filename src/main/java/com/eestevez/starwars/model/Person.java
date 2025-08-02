package com.eestevez.starwars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;


public record Person(
        String name,
        @JsonProperty("birth_year") String birthYear,
        @JsonProperty("eye_color") String eyeColor,
        String gender,
        @JsonProperty("hair_color") String hairColor,
        String height,
        String mass,
        @JsonProperty("skin_color") String skinColor,
        String homeworld,
        OffsetDateTime created,
        OffsetDateTime edited
) {
}
