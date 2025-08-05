package com.eestevez.starwars.service.sort;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class GenericSorter<T> implements Sorter<T> {
    private final Function<T, Comparable> keyExtractor;
    private final String field;

    public GenericSorter(Function<T, Comparable> keyExtractor, String field) {
        this.keyExtractor = keyExtractor;
        this.field = field;
    }

    @Override
    public List<T> sort(List<T> items, boolean ascending) {
        Comparator<T> comparator = Comparator.comparing(keyExtractor);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return items.stream().sorted(comparator).toList();
    }

    @Override
    public String getField() {
        return field;
    }
}
