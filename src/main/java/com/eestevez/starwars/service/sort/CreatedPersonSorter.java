package com.eestevez.starwars.service.sort;

import com.eestevez.starwars.model.Person;
import com.eestevez.starwars.service.PersonSorter;

import java.util.Comparator;
import java.util.List;

public class CreatedPersonSorter implements PersonSorter {
    @Override
    public List<Person> sort(List<Person> people, boolean ascending) {
        Comparator<Person> comparator = Comparator.comparing(Person::created);
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
