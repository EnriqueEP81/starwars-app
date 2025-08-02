package com.eestevez.starwars.service.sort;

import com.eestevez.starwars.model.Person;

import java.util.Comparator;
import java.util.List;

public class NamePersonSorter implements PersonSorter {
    @Override
    public List<Person> sort(List<Person> people, boolean ascending) {
        Comparator<Person> comparator = Comparator.comparing(Person::name);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return people.stream().sorted(comparator).toList();
    }

    @Override
    public String getField() {
        return "name";
    }
}
