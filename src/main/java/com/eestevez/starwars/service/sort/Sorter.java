package com.eestevez.starwars.service.sort;

import java.util.List;

public interface Sorter<T> {
    List<T> sort(List<T> items, boolean ascending);
    String getField();
}
