package com.eestevez.starwars.service.sort;


import com.eestevez.starwars.model.Starship;

import java.util.List;

public interface StarshipSorter {
    List<Starship> sort(List<Starship> starships, boolean ascending);
    String getField();
}
