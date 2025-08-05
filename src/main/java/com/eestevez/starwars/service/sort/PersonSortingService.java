package com.eestevez.starwars.service.sort;

import com.eestevez.starwars.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonSortingService {
    private final Map<String, Sorter<Person>> sorters;

    public PersonSortingService(List<Sorter<Person>> sorterList) {
        this.sorters = sorterList.stream()
                .collect(Collectors.toMap(Sorter::getField, s -> s));
    }

    public List<Person> sort(List<Person> people, String field, boolean ascending) {
        Sorter<Person> sorter = sorters.get(field);
        if (sorter != null) {
            return sorter.sort(people, ascending);
        }
        return people;
    }
}
