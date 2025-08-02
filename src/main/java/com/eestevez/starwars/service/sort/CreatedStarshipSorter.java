package com.eestevez.starwars.service.sort;

import com.eestevez.starwars.model.Starship;

import java.util.Comparator;
import java.util.List;

public class CreatedStarshipSorter implements StarshipSorter {
    @Override
    public List<Starship> sort(List<Starship> people, boolean ascending) {
        Comparator<Starship> comparator = Comparator.comparing(Starship::created);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return people.stream().sorted(comparator).toList();
    }

    @Override
    public String getField() {
        return "created";
    }
}
