package com.eestevez.starwars.service;

import com.eestevez.starwars.model.Person;

import java.util.List;

public interface PersonSorter {
    List<Person> sort(List<Person> people, boolean ascending);
    String getField();
}
