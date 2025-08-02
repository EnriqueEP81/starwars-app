package com.eestevez.starwars.service.sort;

import com.eestevez.starwars.model.Person;
import com.eestevez.starwars.service.PersonSorter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonSortingService {
    private final Map<String, PersonSorter> sorters;

    public PersonSortingService(List<PersonSorter> sorterList) {
        this.sorters = sorterList.stream()
                .collect(Collectors.toMap(PersonSorter::getField, s -> s));
    }

    public List<Person> sort(List<Person> people, String field, boolean ascending) {
        PersonSorter sorter = sorters.get(field);
        if (sorter != null) {
            return sorter.sort(people, ascending);
        }
        return people;
    }
}
