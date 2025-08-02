package com.eestevez.starwars.service.sort;

import com.eestevez.starwars.model.Starship;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StarshipSortingService {
    private final Map<String, StarshipSorter> sorters;

    public StarshipSortingService(List<StarshipSorter> sorterList) {
        this.sorters = sorterList.stream()
                .collect(Collectors.toMap(StarshipSorter::getField, s -> s));
    }

    public List<Starship> sort(List<Starship> starships, String field, boolean ascending) {
        StarshipSorter sorter = sorters.get(field);
        if (sorter != null) {
            return sorter.sort(starships, ascending);
        }
        return starships;
    }
}
