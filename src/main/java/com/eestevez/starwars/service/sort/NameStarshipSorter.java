package com.eestevez.starwars.service.sort;

import com.eestevez.starwars.model.Starship;

import java.util.Comparator;
import java.util.List;

public class NameStarshipSorter implements StarshipSorter {
    @Override
    public List<Starship> sort(List<Starship> starships, boolean ascending) {
        Comparator<Starship> comparator = Comparator.comparing(Starship::name);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return starships.stream().sorted(comparator).toList();
    }

    @Override
    public String getField() {
        return "name";
    }
}
