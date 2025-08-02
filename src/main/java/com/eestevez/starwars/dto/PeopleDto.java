package com.eestevez.starwars.dto;

import com.eestevez.starwars.model.Person;

import java.util.List;

public class PeopleDto {
    private int count;
    private String next;
    private String previous;
    private List<Person> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Person> getResults() {
        return results;
    }

    public void setResults(List<Person> results) {
        this.results = results;
    }
}
